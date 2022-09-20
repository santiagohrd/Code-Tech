package com.Admi.Tech.Safety;

import com.Admi.Tech.Handler.CusSuccHand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class ConfSafe extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    CusSuccHand cusSuccHand;

    @Autowired
    public void confiAuthen(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select correo,password,estado from empleado where correo=?")
                .authoritiesByUsernameQuery("select correo, rol from empleado where correo=?");
    }


    @Override
     protected void configure (HttpSecurity http) throws Exception{
         http.authorizeRequests()
                 .antMatchers("/","VerEmpresas/**").hasRole("ADMIN")
                 .antMatchers("/VerEmpleados/**").hasRole("ADMIN")
                 .antMatchers("/Empresa/**").hasRole("ADMIN")
                 .antMatchers("/Empleado/**").hasRole("ADMIN")
                 .antMatchers("/VerMovimientos/**").hasAnyRole("ADMIN","USER")
                 .antMatchers("/AgregarMovimiento/**").hasAnyRole("ADMIN","USER")
                 .antMatchers("/EditarMovimiento/**").hasAnyRole("ADMIN","USER")
                 .and().formLogin().successHandler(cusSuccHand)
                 .and().exceptionHandling().accessDeniedPage("/Error")
                 .and().logout().permitAll().and().csrf();
     }

}
