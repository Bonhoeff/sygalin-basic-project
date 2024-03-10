package {{.PackageName}};

import {{.PackageName}}.configurations.ApplicationTimeZoneManager;
import {{.PackageName}}.configurations.HttpClient;
import {{.PackageName}}.feignclients.ExampleFeignClient;
import {{.PackageName}}.security.TokenManager;
import {{.PackageName}}.security.TokenManagerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
@EnableFeignClients
public class {{.ClassName}} {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run({{.ClassName}}.class, args);
	}

	@Bean
	public TokenManager tokenManager(){
		return new TokenManagerService("HMAC","1|H","");
	}
	@Bean
	CommandLineRunner commandLineRunner(TokenManager tokenManager, ApplicationTimeZoneManager applicationTimeZoneManager, HttpClient httpClient){
		return args -> {
		/*	ExampleFeignClient http = httpClient.http(ExampleFeignClient.class, "https://jsonplaceholder.typicode.com/");
			http.getPosts().forEach(v->{
				System.out.println("title "+v.getTitle());
			});
			Map<String,String> claims = new HashMap<>();
			LocalDateTime localDateTime = LocalDateTime.now();
			System.out.println(" now hour UTC="+localDateTime.toString()+" Europe/Paris="+applicationTimeZoneManager.moment(localDateTime,"Europe/Paris").toString());
			System.out.println("Test "+tokenManager.generateToken(claims));
			ArrayList<String> keys = new ArrayList<>();
			keys.add("username");
			Map<String, String> claims1 = tokenManager.getClaims(keys, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MDkwMzAwNzgsImlzcyI6IkJBU0lDIiwic3ViIjoiQUNDRVNTIiwidXNlcm5hbWUiOiJkaWV0cmljaCJ9.m2Q_f_Xap4OuqtrPBDopJCs270CtPuYh-CaSt2mB6SU");
		  if (claims1 != null)
		   claims1.forEach((k,v)->{
			   System.out.println("Value "+v);
		   });*/
		};
	}

}
