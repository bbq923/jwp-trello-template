package next;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import javax.annotation.Resource;

///**
// * Created by NEXT on 2017. 7. 27..
// */
//@Configuration
//@EnableWebSecurity
public abstract class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    abstract void configureCsrf(HttpSecurity http) throws Exception;

//    @Value("${spring.queries.users-query}")
//    private String usersQuery;
//
//    @Value("${spring.queries.roles-query}")
//    private String rolesQuery;

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Resource(name = "customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
        configureCsrf(http);

//        http
//            .authorizeRequests()
//
//                .anyRequest().permitAll()
//                .and()
//            .formLogin()
//                .usernameParameter("email")
//                .loginPage("/login")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/", true)
//                .failureForwardUrl("/form")
//                .permitAll()
//                .and()
//            .logout()
//                .permitAll();

        http
            .authorizeRequests()
                .antMatchers("/", "/form").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/boards", true)
                .permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/")
                .permitAll();
//                .and().exceptionHandling()
//                .accessDeniedPage("/loginForm");

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth // in-memory
//            .inMemoryAuthentication()
//                .withUser("user@user.com").password("password").roles("USER");
        auth // user 테이블만 존재할 경우
            .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

//        auth.
//            jdbcAuthentication()
//            .usersByUsernameQuery(usersQuery)
//            .authoritiesByUsernameQuery(rolesQuery)
//            .passwordEncoder(passwordEncoder());
    }



    @Configuration
    @EnableWebSecurity
    @Profile({"local", "dev", "prod"})
    @Slf4j
    @EnableOAuth2Sso
    static class NotTestWebSecurityConfig extends WebSecurityConfig {
        @Override
        void configureCsrf(HttpSecurity http) throws Exception {
            log.info("enable csrf test profile");
//            http.csrf().disable();
//            http.headers().frameOptions().disable();
        }
    }

    @Configuration
    @EnableWebSecurity
    @Profile("test")
    @Slf4j
    @EnableOAuth2Sso
    static class TestWebSecurityConfig extends WebSecurityConfig {
        @Override
        void configureCsrf(HttpSecurity http) throws Exception {
            log.info("disable csrf test profile");
            http.csrf().disable();
        }
    }
}
