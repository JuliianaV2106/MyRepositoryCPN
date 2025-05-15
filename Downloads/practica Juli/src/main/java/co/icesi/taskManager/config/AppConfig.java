package co.icesi.taskManager.config;

import co.icesi.taskManager.utils.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class AppConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtFilter jwtFilter() {return new JwtFilter();}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//            .cors(t -> t.disable())
            .csrf(c -> c.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(requests -> requests
                    .requestMatchers("/login").permitAll()
                            .requestMatchers(HttpMethod.GET, "/tasks/**").hasAuthority("VIEW_TASK")
                            .requestMatchers(HttpMethod.POST, "/tasks").hasAuthority("CREATE_TASK")
                            .requestMatchers(HttpMethod.PUT, "/tasks").hasAuthority("UPDATE_TASK")
                            .requestMatchers(HttpMethod.PUT, "/tasks/**").hasAuthority("UPDATE_TASK")
                            .requestMatchers(HttpMethod.DELETE, "/tasks/**").hasAuthority("DELETE_TASK")

                            .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

            
        return http.build();
    }
}
