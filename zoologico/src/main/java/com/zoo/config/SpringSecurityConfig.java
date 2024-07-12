package com.zoo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public SpringSecurityConfig() {
        super();
    }

    // 

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http
        	.csrf((csrf) -> csrf.disable()) //CSRF está habilitado por defecto y con esto deshabilito
            .formLogin(formLogin -> formLogin
                    .loginPage("/login.html")
                    .failureUrl("/login-error.html"))
            .logout(logout -> logout
                    .logoutSuccessUrl("/index.html"))
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(
                            new AntPathRequestMatcher("/"),
                            new AntPathRequestMatcher("/index.html"),
                            new AntPathRequestMatcher("/login.html"),
                            new AntPathRequestMatcher("/css/**"),
                            new AntPathRequestMatcher("/favicon.ico")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                    .requestMatchers(new AntPathRequestMatcher("/user/**")).hasRole("USER")
                    .requestMatchers(new AntPathRequestMatcher("/shared/**")).hasAnyRole("USER","ADMIN")
                    .anyRequest().authenticated())
            .exceptionHandling(handling -> handling
                    .accessDeniedPage("/403.html"));
        return http.build();
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
    	return new InMemoryUserDetailsManager(
                User.withUsername("jim").password("{noop}demo").roles("ADMIN").build(),
                User.withUsername("bob").password("{noop}demo").roles("USER").build(),
                User.withUsername("ted").password("{noop}demo").roles("USER","ADMIN").build());
    }

}
