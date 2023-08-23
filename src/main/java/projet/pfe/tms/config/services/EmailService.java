package projet.pfe.tms.config.services;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import projet.pfe.tms.config.jwt.JwtUtils;
import projet.pfe.tms.models.Staff;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final Configuration configuration;
	private final JavaMailSender javaMailSender;
	private final JwtUtils jwtUtils;
	@Value("${frontend}")
	private String frontendUrl;

	public void sendEmail(Staff user) throws MessagingException, IOException, TemplateException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		helper.setSubject("Réinitialiser votre mot de passe - PALACIO CAR");
		helper.setTo(user.getEmail());

		String token = jwtUtils.generateToken(user);
		StringWriter stringWriter = new StringWriter();
		Map<String, Object> model = new HashMap<>();
		model.put("fullname", user.getFirstName() + " " + user.getLastName());
		model.put("email", user.getEmail());
		model.put("url", frontendUrl + "auth/reset-password/" + token);
		configuration.getTemplate("reset-password.ftl").process(model, stringWriter);
		String emailContent = stringWriter.getBuffer().toString();
		helper.setText(emailContent, true);
		javaMailSender.send(mimeMessage);
	}

}
