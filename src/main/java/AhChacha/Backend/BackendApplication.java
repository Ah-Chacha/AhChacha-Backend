package AhChacha.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA Auditing 활성화
public class BackendApplication {

	public static void main(String[] args) {
//		SpringApplication.run(BackendApplication.class, args);
		SpringApplication application = new SpringApplication(BackendApplication.class);
		application.addListeners(new ApplicationPidFileWriter());
		application.run(args);
	}

}
