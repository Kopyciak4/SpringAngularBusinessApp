package babackend.BABackend.config;


import babackend.BABackend.filters.JWTAuthenticationFilter;
import babackend.BABackend.filters.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class Authorization extends WebSecurityConfigurerAdapter{

        @Autowired
        DataSource dataSource;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.jdbcAuthentication()
                    .passwordEncoder(NoOpPasswordEncoder.getInstance())
                    .dataSource(dataSource).usersByUsernameQuery(
                            "SELECT login AS username, password, true FROM user WHERE login =  ?"
            ).authoritiesByUsernameQuery(
                    "SELECT login AS username, 'ROLE_USER' FROM user WHERE login = ?"
            );

        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()   .cors().and()
                    .authorizeRequests()
                    .antMatchers("/account/login").permitAll()
                    .anyRequest()
                    .authenticated()
                    .and().addFilterBefore(new JWTLoginFilter("/account/login", authenticationManager()),
                    UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .httpBasic();



        }
}

