package com.example.project.EcommerceApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.project.EcommerceApp.service.CustomeUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        @Autowired
        GoogleOauth2SuccessHandler googleOauth2SuccessHandler;

        @Autowired
        CustomeUserDetailsService customeUserDetailsService;

        private static final String[] WHITELIST = {
                        "/",
                        "/shop/**",
                        "/resources/**",
                        "/register",
                        "/static/**",
                        "/templates/**",
                        "/images/**",
                        "/productImages/**",
                        "/css/**",
                        "/js/**"
        };

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf((csrf) -> csrf.disable())
                                .authorizeHttpRequests(
                                                (authorize) -> authorize
                                                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                                                .requestMatchers(WHITELIST)
                                                                .permitAll()
                                                                .anyRequest()
                                                                .authenticated())
                                .formLogin(formLogin -> formLogin
                                                .loginPage("/login")
                                                .failureUrl("/login?error=true")
                                                .defaultSuccessUrl("/")
                                                .usernameParameter("email")
                                                .passwordParameter("password")
                                                .permitAll())
                                .rememberMe(Customizer.withDefaults())
                                .oauth2Login(oauth2login -> oauth2login
                                                .loginPage("/login")
                                                .successHandler(googleOauth2SuccessHandler))
                                .logout(logout -> logout
                                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                                .logoutSuccessUrl("/login").permitAll()
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID"))
                                .httpBasic(Customizer.withDefaults());
                return http.build();
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
                return (web) -> web.ignoring().requestMatchers("/resources/**", "/static/**", "/images/**",
                                "/productImages/**", "/css/**", "/js/**");
        }

        @Bean
        AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

}
