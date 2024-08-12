package com.easyum.springsec.config;

import com.easyum.springsec.config.auth.UserEntityDetailsService;
import com.easyum.springsec.repo.UserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private UserRepo userRepo;

    public WebSecurityConfig(final DataSource dataSource, final UserRepo userRepo) {
        this.dataSource = dataSource;
        this.userRepo = userRepo;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("configure http");
        http.authorizeRequests()
                .antMatchers("/reg").permitAll()
                .antMatchers("/hello").hasRole("USER")
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .successForwardUrl("/hello")
                .permitAll()
            .and()
                .logout()
                .permitAll()
            .and()
                .httpBasic();
    }


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("configure auth");
        auth.userDetailsService(new UserEntityDetailsService(userRepo));
    }

//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println("configure auth");
//        auth.jdbcAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, 1 from user where username = ?")
//                .authoritiesByUsernameQuery("select username, 'USER' as roles from user where username = ?");
//    }

//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println("configure auth");
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
//                .withUser("sasha")
//                .password(passwordEncoder().encode("sasha"))
//                .roles("USER")
//            .and()
//                .withUser("anna")
//                .password(passwordEncoder().encode("anna"))
//                .roles("USER")
//            .and()
//                .withUser("ivan")
//                .password(passwordEncoder().encode("ivan"))
//                .roles("USER");
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}