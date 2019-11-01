package mc.bc.ms.institute.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	
	@Value("${coursePort:8002}")
    private String coursePort;
	
	@Bean
	public WebClient createWebClient() {
		return WebClient.create("http://localhost:"+coursePort+"/courses");
	}
}
