package com.devtobz.hotelmanagementsystem.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig{

    @Autowired
    private EmployeeUserDetailsService employeeUserDetailsService;
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable).
                cors(Customizer.withDefaults()).
                authorizeHttpRequests(auth->{
                    auth.anyRequest().permitAll();
                    //auth.requestMatchers("/login/**").permitAll();
                   // auth.anyRequest().authenticated();
                }).
                sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                authenticationProvider(authenticationProvider()).
                addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
     DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
     dao.setUserDetailsService(employeeUserDetailsService);
     dao.setPasswordEncoder(passwordEncoder());
     return dao;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
