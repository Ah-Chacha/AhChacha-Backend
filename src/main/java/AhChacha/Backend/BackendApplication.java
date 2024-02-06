package AhChacha.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
//		SpringApplication.run(BackendApplication.class, args);
		SpringApplication application = new SpringApplication(BackendApplication.class);
		application.addListeners(new ApplicationPidFileWriter());
		application.run(args);
	}

}
