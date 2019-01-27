package ru.javastudy.mvc.javaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created for JavaStudy.ru on 29.05.2016.
 * security-config.xml analogue
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("SELECT USERNAME, PASSWORD, ENABLED FROM USER WHERE USERNAME=?")
            .authoritiesByUsernameQuery("SELECT U.USERNAME, A.AUTHORITY "
                + "FROM AUTHORITIES A, USER U WHERE U.USERNAME = A.USERNAME AND U.USERNAME = ?");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .anyRequest().authenticated() //all requests will checked
            .and()
            .formLogin().loginPage("/login.html").permitAll().usernameParameter("j_username")
            .passwordParameter("j_password").loginProcessingUrl("/j_spring_security_check")
            .failureUrl("/login.html?error=true")
            .and()
            .authorizeRequests().antMatchers("/security/**").hasRole("ADMIN")
            .antMatchers("/user/**").hasRole("USER")
            .and()
            .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/")
            .and()
            .rememberMe().key("myKey").tokenValiditySeconds(300)
            .and()
            .csrf().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
