package projet.pfe.tms;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import projet.pfe.tms.enums.TaskPriority;
import projet.pfe.tms.enums.TaskRepeatEvery;
import projet.pfe.tms.enums.TaskStatus;
import projet.pfe.tms.models.Task;
import projet.pfe.tms.repositories.TaskRepo;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableWebMvc
@SpringBootApplication
@EnableSwagger2
@CrossOrigin("*")
public class TmsApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(TmsApplication.class, args);
	}


	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("projet.pfe.tms.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"My REST API",
				"Some custom description of API.",
				"1.0",
				"Terms of service",
				new Contact("Sallo Szrajbman", "www.baeldung.com", "salloszraj@gmail.com"),
				"License of API",
				"API license URL",
				Collections.emptyList());
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

	@Autowired
	TaskRepo taskRepo;
	@Override
	public void run(String... args) throws Exception {
//		for (int i = 1; i < 20; i++) {
//			Task task = new Task();
//			task.setName("Task "+i);
//			task.setTaskDetails("Description task number "+i);
//			task.setStatus(TaskStatus.SCHEDULED);
//			task.setPriority(TaskPriority.IMPORTANT);
//			task.setRepeatEvery(TaskRepeatEvery.MONTH);
//			task.setPlanNextAction(LocalDateTime.now());
//			task.setDueDate(LocalDateTime.now());
//			taskRepo.save(task);
//		}
	}
}
