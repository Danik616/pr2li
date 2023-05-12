package com.pruebas.liti;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import static org.mockito.Mockito.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import com.pruebas.liti.entity.UserEntityProof;
import com.pruebas.liti.security.AuthenticationManager;
import com.pruebas.liti.services.UserServices;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@ActiveProfiles("test")
public class SecurityTest {

    @Autowired
    private ReactiveUserDetailsService userDetailsService;

    @Mock
    private UserServices userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private BCryptPasswordEncoder passwordEncode1;

    @InjectMocks
    private AuthenticationManager authenticationManager;

    @Test
    @WithMockUser(username = "example@example.com", password = "Test1234")
    public void testAuthenticate() {


        // Call the authentication manager and expect a successful authentication
        Mono<UserDetails> userDetailsMono = userDetailsService.findByUsername("example@example.com");
        StepVerifier.create(userDetailsMono)
                .assertNext(userDetails -> {
                    boolean matches = passwordEncoder.matches("Test1234", userDetails.getPassword());
                    assert matches : "Password does not match";
                })
                .expectComplete()
                .verify();
    }

    /**
     * 
     */
    @Test
void testAboutAuthenticateUDRepository() {
    // Crea un usuario ficticio con credenciales válidas
    String email = "example@example.com";
    String password = "Test1234";
    UserDetails user = User.builder()
            .username(email)
            .password(passwordEncoder.encode(password))
            .roles("GUESS")
            .build();
    
    // Crea un UserDetailsRepositoryReactiveAuthenticationManager y configúralo con un UserDetailsRepository
    UserServices userDetailsRepository = mock(UserServices.class);
    when(userDetailsRepository.findByUsername(email)).thenReturn(Mono.just(user));
    UserDetailsRepositoryReactiveAuthenticationManager authManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsRepository);
    authManager.setPasswordEncoder(new BCryptPasswordEncoder());

    // Crea un AuthenticationToken con las credenciales del usuario ficticio
    Authentication auth = new UsernamePasswordAuthenticationToken(email, password);

    // Prueba que la autenticación tenga éxito
    StepVerifier.create(authManager.authenticate(auth))
            .expectNextMatches(authentication -> authentication.isAuthenticated())
            .expectComplete()
            .verify();
}

@Test
public void testAboutAuthenticateAM() {
    // Crea un usuario de prueba en la base de datos
    UserEntityProof userEntity = new UserEntityProof();
    userEntity.setEmail("example@example.com");
    userEntity.setPassword(passwordEncoder.encode("Test1234"));

    // Crea un objeto UsernamePasswordAuthenticationToken con las credenciales del usuario
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("example@example.com", "Test1234");
    // Crea una instancia de AuthenticationManager
    AuthenticationManager authManager = new AuthenticationManager();
    authManager.userService=userService;
    authManager.passwordEncoder = passwordEncoder;

    // Llama al método authenticate() y verifica que el resultado sea un objeto Authentication válido
    StepVerifier.create(authManager.authenticate(token))
        .expectNextMatches(auth -> auth.isAuthenticated() && auth.getPrincipal().equals("testuser@example.com"))
        .verifyComplete();

}

@Test
public void testRepository(){
    String email= "example@example.com";

    Mono<UserDetails> userDetailsMono = userDetailsService.findByUsername(email);

    StepVerifier.create(userDetailsMono)
                .assertNext(userDetails -> {
                    boolean matches = userDetails.getUsername().equals(email);
                    assert matches : "Password does not match";
                })
                .expectComplete()
                .verify();
}

}
