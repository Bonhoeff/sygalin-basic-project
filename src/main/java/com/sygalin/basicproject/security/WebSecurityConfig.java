package {{.PackageName}}.security;

import {{.PackageName}}.security.filters.IdentificationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf)->csrf.disable());
        http.authorizeHttpRequests((authz)->authz
                .anyRequest().permitAll()
        );
        http.sessionManagement((session)->session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.addFilterBefore(new IdentificationFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }
}
