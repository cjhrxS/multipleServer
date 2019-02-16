package ua.ita.smartcarservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ua.ita.smartcarservice.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.cors().and().csrf().disable()

                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/skillbysto/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/skillByCar/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/workerBySkill").permitAll()
                .antMatchers(HttpMethod.POST, "/api/workerByCar/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/addBooking").permitAll()
                .antMatchers(HttpMethod.POST, "/api/sessionById").permitAll()
                .antMatchers(HttpMethod.POST, "/api/bookingTime").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/skills").permitAll()

                .antMatchers(HttpMethod.POST, "/api/record/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/chart/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/faultCode/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/notifications/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/notifications/**").permitAll()
                .antMatchers(HttpMethod.GET, "/car/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/car/**").permitAll()
                .antMatchers(HttpMethod.POST, "/ucar/**").permitAll()
                .antMatchers(HttpMethod.GET, "/ownercars/**").permitAll()
                .antMatchers(HttpMethod.GET, "/carbyvin/**").permitAll()
                .antMatchers(HttpMethod.GET,"/cars/all/**").permitAll()
                .antMatchers(HttpMethod.GET,"/users/all/**").permitAll()
                .antMatchers(HttpMethod.GET,"/userbyid/**").permitAll()
                .antMatchers(HttpMethod.GET,"/userbyname/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/user/**").permitAll()
                .antMatchers(HttpMethod.POST,"/newuser/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/workers/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll()
                .antMatchers(HttpMethod.GET, "/userbyname/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/techservices/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/v1/techservices/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


}
