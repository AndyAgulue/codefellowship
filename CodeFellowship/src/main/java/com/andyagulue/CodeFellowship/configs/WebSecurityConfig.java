package com.andyagulue.CodeFellowship.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.andyagulue.CodeFellowship.models.ApplicationUserRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImplementation userDetailsServiceImplementation;

    @Bean
    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder // attach out userdetailsService to the auth Manager and the passwordEncoder
                    .userDetailsService(userDetailsServiceImplementation)
                    .passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean()throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        // we do all the work to decide how users can access the site
      httpSecurity
                .cors().disable()
                .csrf().disable() //cross site resource forgery (heroku needs this)

                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/applicationUser", "/login").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .defaultSuccessUrl("/")
                .loginPage("/login")

                .and()
                .logout()
                .logoutSuccessUrl("/login");

    }
}
