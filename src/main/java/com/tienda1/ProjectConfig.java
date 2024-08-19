package com.tienda1;

import com.paypal.base.rest.APIContext;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {
    
    @Bean
    public LocaleResolver localeResolver(){
        var slr= new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }
    
    
    // Los siguientes metodos son para realizar la parte de autenticacion y autorizacion
    @Override 
    public void addViewControllers(ViewControllerRegistry registro){
        registro
                .addViewController("/")
                .setViewName("index");
                
        registro
                .addViewController("/index")
                .setViewName("index");
        registro
                .addViewController("/login")
                .setViewName("login");
        registro
                .addViewController("/registro/nuevo")
                .setViewName("/registro/nuevo");
        
    }
    
    // definir la autorizacion 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/","/index","/errores/**",
                        "/carrito/**",
                        "/registro/**","/js/**","/webjars/**")
                        .permitAll()
                .requestMatchers(
                        "/producto/nuevo","/producto/guardar",
                        "/producto/modificar/**","/producto/eliminar/**",
                        "/categoria/nuevo","/categoria/guardar",
                        "/categoria/modificar/**","/categoria/eliminar/**",
                        "/usuario/nuevo","/usuario/guardar",
                        "/usuario/modificar/**","/usuario/eliminar/**",
                        "/reportes/**", "/pruebas/**"
                ).hasRole("ADMIN")
                .requestMatchers(
                        "/producto/listado",
                        "/categoria/listado",
                        "/usuario/listado"
                ).hasAnyRole( "VENDEDOR")
                .requestMatchers("/facturar/carrito")
                .hasRole("USER")
                )
                .formLogin((form) -> form
                .loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }
    
    // definir la parte de autorizacion
    // este metodo se utilzia esta semana pero se realizara un reemplazo la proxima
    /* @Bean
    public UserDetailsService users (){
        UserDetails admin, vendedor, usuario;
        admin = User.builder()
                .username("juan")
                .password("{noop}123")
                .roles("ADMIN","VENDEDOR","USER")
                .build();
        
        vendedor = User.builder()
                .username("rebeca")
                .password("{noop}456")
                .roles("VENDEDOR","USER")
                .build();
        
        usuario = User.builder()
                .username("pedro")
                .password("{noop}789")
                .roles("USER")
                .build();
        
        return new InMemoryUserDetailsManager(admin, vendedor, usuario);
    } */
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) 
        throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    // para integracion con paypal
    @Value("${paypal.client-id}")
    private String clientID;
    @Value("${paypal.cliente-secret}")
    private String clientSecret;
    @Value("${paypal.mode}")
    private String mode;
    
    @Bean
    public APIContext apiContext (){
        return new APIContext (clientID, clientSecret, mode);
    }
}
