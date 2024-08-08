package com.curso_api.config.security;

import com.curso_api.exception.ObjectNotFoundException;
import com.curso_api.persistence.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityBeansInjector {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final IUserRepository userRepository;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        // Proporciona una instancia de AuthenticationManager, que es responsable de gestionar la autenticación.
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        // Crea una instancia de DaoAuthenticationProvider, que es una implementación de AuthenticationProvider
        // utilizada para autenticar al usuario con datos provenientes de una base de datos.
        DaoAuthenticationProvider authenticationStrategy = new DaoAuthenticationProvider();
        // Establece el codificador de contraseñas a utilizar, en este caso BCryptPasswordEncoder.
        authenticationStrategy.setPasswordEncoder(passwordEncoder());
        // Establece el servicio que recupera los detalles del usuario.
        authenticationStrategy.setUserDetailsService(userDetailsService());
        return authenticationStrategy;
    }

    // -- Creacion despues de la clase User, Role, RolePermission
    @Bean
    public PasswordEncoder passwordEncoder(){
        // Proporciona una instancia de BCryptPasswordEncoder, que se utiliza para codificar las contraseñas de los usuarios.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        // Proporciona una implementación de UserDetailsService, que es responsable de cargar los detalles del usuario
        // por nombre de usuario. Aquí, se utiliza el repositorio de usuario para buscar el usuario en la base de datos.
        return (username) -> {
            return userRepository.findByUsername(username)
                    .orElseThrow(()-> new ObjectNotFoundException("El usuario: " + username + " no fue encontrado."));
        };
    }

}
