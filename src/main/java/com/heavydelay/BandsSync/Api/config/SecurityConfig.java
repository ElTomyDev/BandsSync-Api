package com.heavydelay.BandsSync.Api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    /*
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> {
                //Configuracion para los endpoints de admin
                if (adminEndpointsEnable){
                    auth.requestMatchers(new AntPathRequestMatcher("/sys/api/v1/user/admin")).permitAll();
                } else {
                    auth.requestMatchers(new AntPathRequestMatcher("/sys/api/v1/user/admin")).denyAll();
                }

                //Configuracion general para todos los demas endpoints
                auth.anyRequest().permitAll();
            })
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    */

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder.encode("112233"))
            .roles("ADMIN")
            .build();
        
        return new InMemoryUserDetailsManager(admin);
    }
}
