package com.example.carrental.Config;

import com.example.carrental.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/users/register/owner","/api/v1/users/register/customer","/api/v1/users/login","/api/v1/violations").permitAll()

                .requestMatchers("/api/v1/users","/api/v1/cars/add","/api/v1/cars/update/**","/api/v1/cars/delete/**","/api/v1/insurance/add","/api/v1/cars/assign/car/**","/api/v1/violations/assing/customer/**","/api/v1/insurance/update/**","/api/v1/insurance/delete/**","/api/v1/isReserved/assing/**").hasAuthority("Owner")

                .requestMatchers("/api/v1/users","api/v1/users/update/customer","/api/v1/booking/rent/**","/api/v1/booking/cancel/**","/api/v1/booking/check/**","/api/v1/booking/payment/**","/api/v1/violations/payment/**","/api/v1/customers","/api/v1/customers/mycar/**").hasAuthority("Customer")

                .requestMatchers("/api/v1/violations/add","/api/v1/violations/update/**","/api/v1/violations/delete/**","/api/v1/violations/assign","/api/v1/violations/assign/unpaid","/api/v1/isReserved","/api/v1/isReserved/all","/api/v1/isReserved/add","/api/v1/isReserved/update/**","/api/v1/booking/assingcar/**").hasAnyAuthority("Admin","Owner")

                .requestMatchers("/api/v1/users","/api/v1/booking/all","/api/v1/booking/add","/api/v1/booking/update/**","/api/v1/booking/delete/**","/api/v1/booking/black_list/**","/api/v1/cars/ascending","/api/v1/cars/dscending","/api/v1/cars/all","/api/v1/cars/owner/name/**","/api/v1/booking/check/**").hasAnyAuthority("Customer" , "Admin","Owner")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }

}
