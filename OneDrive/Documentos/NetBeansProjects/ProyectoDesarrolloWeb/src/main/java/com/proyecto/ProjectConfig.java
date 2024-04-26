package com.proyecto;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.LocaleResolver; 
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registro) {
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/index").setViewName("index");
        registro.addViewController("/login").setViewName("login");
        registro.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }
    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((requests) -> requests
        // Permitir acceso sin autenticación a las siguientes rutas
        .requestMatchers("/", "/index", "/carrito/**", "/js/**", "/webjars/**").permitAll()
        .requestMatchers("/categoria/listado", "/producto/listado", "/cita/listado", "/grooming/listado",
                         "/mascota/listado", "/consulta/listado", "/cuidado/listado").hasAnyRole("VENDEDOR", "ADMIN")
        // Permitir acceso sin autenticación a las rutas de agregar nuevo
        .requestMatchers("/categoria/nuevo", "/producto/nuevo", "/cita/nuevo", "/grooming/nuevo",
                         "/mascota/nuevo", "/consulta/nuevo", "/cuidado/nuevo").permitAll()
        .requestMatchers("/categoria/modificar/**", "/categoria/eliminar/**", "/categoria/guardar",
                         "/producto/modificar/**", "/producto/eliminar/**", "/producto/guardar",
                         "/cita/modificar/**", "/cita/eliminar/**", "/cita/guardar",
                         "/grooming/modificar/**", "/grooming/eliminar/**", "/grooming/guardar",
                         "/mascota/modificar/**", "/mascota/eliminar/**", "/mascota/guardar",
                         "/consulta/modificar/**", "/consulta/eliminar/**", "/consulta/guardar",
                         "/cuidado/modificar/**", "/cuidado/eliminar/**", "/cuidado/guardar").hasAnyRole("ADMIN")
        .requestMatchers("/facturar/carrito").hasAnyRole("USER"))
        .formLogin((form) -> form.loginPage("/login").permitAll())
        .logout((logout) -> logout.permitAll());
    return http.build();
}
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    
}
