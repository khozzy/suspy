package com.pwr.suspy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Value("${rememberMe.privateKey}")
    private String rememberMeKey;

    @Resource
    private UserDetailsService userService;

    @Bean
    public RememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices(rememberMeKey, userService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.info("Creating password encoder bean");
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/",
                        "/home",
                        "/error",
                        "/signup",
                        "/signupJson",
                        "/service/**",
                        "/forgot-password",
                        "/event/*",
                        "/reset-password/*",
                        "/public/**",
                        "/users/*")
                .permitAll()
                .anyRequest()
                .authenticated();

        http
                .formLogin()
                .loginPage("/login")
                .permitAll().and()
                .rememberMe().key(rememberMeKey).rememberMeServices(rememberMeServices()).and()
                .logout()
                .permitAll();

        http.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {

            private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
            private RegexRequestMatcher apiMatcher = new RegexRequestMatcher("/service/.*", null);

            @Override
            public boolean matches(HttpServletRequest httpServletRequest) {
                if (allowedMethods.matcher(httpServletRequest.getMethod()).matches()) {
                    return false;
                }

                if (apiMatcher.matches(httpServletRequest)) {
                    return false;
                }

                return true;
            }
        });
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

}