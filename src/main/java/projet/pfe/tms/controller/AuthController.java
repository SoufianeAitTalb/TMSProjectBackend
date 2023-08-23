package projet.pfe.tms.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import projet.pfe.tms.config.jwt.JwtUtils;
import projet.pfe.tms.config.request.ChangePasswordRequest;
import projet.pfe.tms.config.request.LoginRequest;
import projet.pfe.tms.config.response.JwtResponse;
import projet.pfe.tms.config.response.MessageResponse;
import projet.pfe.tms.config.services.EmailService;
import projet.pfe.tms.config.services.UserDetailsImpl;
import projet.pfe.tms.models.Staff;
import projet.pfe.tms.repositories.StaffRepo;
import projet.pfe.tms.services.AccountService;
import projet.pfe.tms.services.StaffService;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private StaffRepo staffRepository;
    private EmailService emailService;
    private AccountService accountService;
    private StaffService staffService;
    private StaffRepo userRepo;
    private JwtUtils jwtUtils;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          StaffRepo staffRepository,
                          EmailService emailService,
                          StaffService staffService,
                          AccountService accountService,
                          JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.staffRepository = staffRepository;
        this.emailService = emailService;
        this.staffService = staffService;
        this.jwtUtils = jwtUtils;
        this.accountService = accountService;
        this.passwordEncoder=passwordEncoder;
    }



    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) throws IOException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));


            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            if (!userDetails.getEmail().isEmpty()) {
                Staff user = staffRepository
                        .findByEmail(userDetails.getEmail())
                        .orElse(null);

                if (user != null && !user.isAllowed()) {
                    return ResponseEntity.badRequest()
                            .body("Votre compte n'a pas encore été activé par l'administrateur");
                }
                if (user != null && !user.isActived()) {
                    return ResponseEntity.badRequest().body("Votre compte a été désactivé par l'administrateur");
                }
            }

            List<String> roles = userDetails
                                    .getAuthorities()
                                    .stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList());

//			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
//					.collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(),
                    userDetails.getEmail(), userDetails.getFirstName(), userDetails.getLastName(), roles));

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Nom d'utilisateur ou mot de passe invalide");
        }
    }

    // This API check if the user is logged in or not

    @PostMapping(path = "/authenticated")
    public ResponseEntity<?> isLoggedIn(@RequestBody JwtResponse jwtResponse) {
        if (jwtResponse.getId() != null && jwtResponse.getAccessToken() != null) {
            try {
                String email = jwtUtils.getEmailFromJwtToken(jwtResponse.getAccessToken());
                if (email != null) {
                    Staff staff = staffRepository
                            .findByStaffId(jwtResponse.getId())
                            .orElse(null);

                    if (staff != null && staff.getEmail().equals(email)) {

                        if (!staff.isAllowed()) {
                            return ResponseEntity.badRequest()
                                    .body("Votre compte n'est pas autorisé à se connecter");
                        }

                        if (!staff.isActived()) {
                            return ResponseEntity.badRequest()
                                    .body("Votre compte a été désactivé par l'administrateur");
                        }

                        return ResponseEntity.ok(true);
                    }
                }
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Votre session a expiré, veuillez vous reconnecter");
            }
        }
        return ResponseEntity.badRequest().body(false);
    }

    // this API send email to the staff to reset password

    @GetMapping(path = "/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) throws ParseException {
        if (staffRepository.existsByEmail(email)) {

            Staff user = staffRepository.findByEmail(email).get();

            if (!user.isActived()) {
                return ResponseEntity.badRequest()
                        .body("Ce compte a été désactivé, veuillez contacter l'administration");
            }
            if (!user.isAllowed()) {
                return ResponseEntity.badRequest()
                        .body("Votre compte n'est pas autorisé à se connecter");
            }
            try {
                emailService.sendEmail(user);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Erreur dans le serveur : " + e.getMessage());
            }
            return ResponseEntity.ok(new MessageResponse("Vérifiez votre boîte de réception"));
        }
        return ResponseEntity.badRequest().body("Aucun utilisateur n'existe avec cet email !");
    }


    @GetMapping(path = "/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String password) {
        try {
            Staff user = staffRepository
                    .findByEmail(jwtUtils.getEmailFromJwtToken(token))
                    .orElse(null);

            if (user != null && user.isActived() && user.isAllowed() ) {
                if(this.accountService.resetPassword(user, password) != null)
                    return ResponseEntity.ok(new MessageResponse("Le mot de passe a été réinitialisé avec succès"));
            } else {
                return ResponseEntity.badRequest()
                        .body("Vous ne pouvez pas effectuer cette opération. Pour plus d'informations, " +
                                "veuillez contacter l'administration");
            }
        } catch (Exception exception) {
        }
        return ResponseEntity
                .badRequest()
                .body("Le temps imparti pour effectuer l'opération de réinitialisation a expiré. Veuillez réessayer");
    }
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);

        String email = jwtUtils.getEmailFromJwtToken(token);
        Staff user = staffRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Incorrect old password");
        }
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmedPassword())) {
            return ResponseEntity.badRequest().body("New password and confirmed password do not match");
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        staffRepository.save(user);
        return ResponseEntity.ok("Password changed successfully");
    }
}