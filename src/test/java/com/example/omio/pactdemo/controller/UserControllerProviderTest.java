package com.example.omio.pactdemo.controller;

import au.com.dius.pact.provider.PactVerification;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.example.omio.pactdemo.models.User;
import com.example.omio.pactdemo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = "server.port=8080")
@Provider("userservice")
@PactFolder("/Users/renukabrandur/IdeaProjects/pact-demo/src/test/resources/pacts/")
public class UserControllerProviderTest {

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setupTestTarget(PactVerificationContext pactVerificationContext) {
        pactVerificationContext.setTarget(new HttpTestTarget("localhost", 8080, "/"));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext pactVerificationContext) {
        pactVerificationContext.verifyInteraction();
    }

    @State({"provider accepts a new person"})
    public void toCreatePersonState() {
        User user = new User();
        user.setId(42L);
        user.setFirstName("Test User first name");
        user.setLastName("Test User last name");
        //Mockito.when(userRepository.findById(Mockito.eq(42L))).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

    }
}
