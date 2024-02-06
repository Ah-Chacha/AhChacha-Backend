package AhChacha.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class BackendApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "/app/config/springboot-webservice/real-application.yml";

	public static void main(String[] args) {
//		SpringApplication.run(BackendApplication.class, args);
//     새로운 파일이 commit 되면, 이전 버전은 중단시킨 후 업데이트 된 jar를 실행하기 위해 pid command 값 설정 해주기
		SpringApplication application = new SpringApplication(BackendApplication.class);
		application.addListeners(new ApplicationPidFileWriter());
		application.run(args);
	}

}
