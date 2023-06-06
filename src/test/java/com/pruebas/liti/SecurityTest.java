package com.pruebas.liti;

import org.junit.jupiter.api.Test;
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

import com.pruebas.liti.Repository.IRolProofRepository;
import com.pruebas.liti.Repository.IUserProofRepository;
import com.pruebas.liti.entity.RolEntityProof;
import com.pruebas.liti.entity.UserEntityProof;
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
    IUserProofRepository repository;

    @Autowired
    IRolProofRepository rolRepository;

//     @Test
//     @WithMockUser(username = "prueba3@gmail.com", password = "Test12345")
//     public void testAuthenticate() {


//         // Call the authentication manager and expect a successful authentication
//         Mono<UserDetails> userDetailsMono = userDetailsService.findByUsername("prueba3@gmail.com");
//         StepVerifier.create(userDetailsMono)
//                 .assertNext(userDetails -> {
//                     boolean matches = passwordEncoder.matches("Test12345", userDetails.getPassword());
//                     assert matches : "Password does not match";
//                 })
//                 .expectComplete()
//                 .verify();
//     }

//     /**
//      * 
//      */
//     @Test
// void testAboutAuthenticateUDRepository() {
//     // Crea un usuario ficticio con credenciales válidas
//     String email = "prueba3@gmail.com";
//     String password = "Test12345";
//     UserDetails user = User.builder()
//             .username(email)
//             .password(passwordEncoder.encode(password))
//             .roles("GUESS")
//             .build();
    
//     // Crea un UserDetailsRepositoryReactiveAuthenticationManager y configúralo con un UserDetailsRepository
//     UserServices userDetailsRepository = mock(UserServices.class);
//     when(userDetailsRepository.findByUsername(email)).thenReturn(Mono.just(user));
//     UserDetailsRepositoryReactiveAuthenticationManager authManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsRepository);
//     authManager.setPasswordEncoder(new BCryptPasswordEncoder());

//     // Crea un AuthenticationToken con las credenciales del usuario ficticio
//     Authentication auth = new UsernamePasswordAuthenticationToken(email, password);

//     // Prueba que la autenticación tenga éxito
//     StepVerifier.create(authManager.authenticate(auth))
//             .expectNextMatches(authentication -> authentication.isAuthenticated())
//             .expectComplete()
//             .verify();
// }


@Test
public void testRepository(){
    String email= "MANUEL_VEGA";

    Mono<UserDetails> userDetailsMono = userService.findByUsername(email.toUpperCase());

    StepVerifier.create(userDetailsMono)
                .assertNext(userDetails -> {
                    boolean matches = userDetails.getUsername().equals(email.toUpperCase());
                    assert matches : "email does not match";
                })
                .expectComplete()
                .verify();
}

@Test
public void test1(){
    String email= "MANUEL_VEGA";
    

    Mono<UserEntityProof> pruebaMono = repository.findByIdUser(email.toUpperCase());
    StepVerifier.create(pruebaMono)
                .assertNext(user -> {
                    boolean matches = user.getUsuarioId().equals(email.toUpperCase());
                    assert matches : "email does not match";
                })
                .expectComplete()
                .verify();
}

@Test
public void test2(){
    String email= "MANUEL_VEGA";
    String password= "Alejosmurf07*";
    

    Mono<Integer> pruebaMono = repository.validatePassword(email.toUpperCase(), password).single();
    StepVerifier.create(pruebaMono)
                .assertNext(valid -> {
                    boolean matches = valid==1?true: false;
                    assert matches : "email does not match";
                })
                .expectComplete()
                .verify();
}

@Test
public void testreporol(){
    

    Mono<RolEntityProof> pruebaMono = rolRepository.findById(1L);
    StepVerifier.create(pruebaMono)
                .assertNext(valid -> {
                    boolean matches = valid.getId()==1?true:false;
                    assert matches : "rol does not match"+valid.getId()+valid.getNombre();
                })
                .expectComplete()
                .verify();
}

}
