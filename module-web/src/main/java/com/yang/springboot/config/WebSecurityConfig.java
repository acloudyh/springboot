//package com.yang.springboot.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author yanghao
// * @date 2019-05-15 11:44
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
////    @Autowired
////    private UserDetailsService userDetailsService;
////
////    @Bean
////    public UserDetailsService userDetailsService() {
////        return super.userDetailsService();
////    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //user Details Service验证
//        auth.userDetailsService(this.userDetailsService());
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/web/neo/**").permitAll()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                .anyRequest().authenticated();
//    }
//
//    @Component
//    public static class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
//
//        @Override
//        public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "拒绝访问");
//        }
//    }
//
//}
