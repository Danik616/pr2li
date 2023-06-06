package com.pruebas.liti;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

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



    @Mock
    private UserServices userService;

    @Autowired
    IUserProofRepository repository;

    @Autowired
    IRolProofRepository rolRepository;

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
