package com.example.demo.data;

import com.example.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseDeDonneesTests {

    @Test
    void main() {
        // Teste que la méthode principale de l'application s'exécute sans crasher
        DemoApplication.main(new String[] {});
    }
}