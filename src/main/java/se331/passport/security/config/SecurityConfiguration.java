package se331.passport.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.headers((headers) -> {
      headers.frameOptions((frameOptions) -> frameOptions.disable());
    });
    http
            .csrf((crsf) -> crsf.disable())
            .authorizeHttpRequests((authorize) -> {
                authorize.requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers(HttpMethod.POST, "/uploadImage").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/members/me").authenticated()
                        .requestMatchers(HttpMethod.GET, "/members/me/exam-histories").authenticated()
                        .requestMatchers(HttpMethod.GET, "/members/me/level-detail").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/members/me").authenticated()
                        .requestMatchers("/members/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/questions/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/questions/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/questions/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/questions/**").hasRole("ADMIN")
                        .requestMatchers("/levels/**").hasRole("ADMIN")
                        .requestMatchers("/benefits/**").hasRole("ADMIN")
                        .requestMatchers("/services/**").hasRole("ADMIN")
                        .anyRequest().authenticated();
            })

            .sessionManagement((session) ->{
              session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })

            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .logout((logout) -> {
              logout.logoutUrl("/api/v1/auth/logout");
              logout.addLogoutHandler(logoutHandler);
              logout.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
            });

    return http.build();

  }
}
