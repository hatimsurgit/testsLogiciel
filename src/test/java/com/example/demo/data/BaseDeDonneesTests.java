package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class BaseDeDonneesTests {

    @Test
    void main() {
        // Teste que la méthode principale de l'application s'exécute sans crasher
        DemoApplication.main(new String[] {});
    }
}
