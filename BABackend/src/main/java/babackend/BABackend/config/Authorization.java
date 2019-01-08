package babackend.BABackend.config;

import babackend.BABackend.domain.Account;
import babackend.BABackend.domain.User;
import babackend.BABackend.filters.JWTAuthenticationFilter;
import babackend.BABackend.filters.JWTLoginFilter;
import babackend.BABackend.services.AccountService;
import babackend.BABackend.services.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class Authorization extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    PasswordEncoder passwordEncoder;



    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder)
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
                .and().logout().logoutUrl("/account/logout").logoutSuccessHandler(new LogoutHandler())
                .and().addFilterBefore(new JWTLoginFilter(new AntPathRequestMatcher("/account/login", HttpMethod.GET.toString()), authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic();
    }



}


class LogoutHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {

        TokenAuthenticationService.saveTokenToBlacklist(req);

        if(auth != null && auth.getDetails()!= null){
            req.getSession().invalidate();
        }
       res.setStatus(HttpStatus.OK.value());
    }

}