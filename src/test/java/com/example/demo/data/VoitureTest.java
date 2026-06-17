package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VoitureTest {

    @Test
    void creerVoiture(){

        // On crée la voiture
        Voiture voiture = new Voiture("Renault", 15000);
        voiture.setId(1);

        // On vérifie que la voiture a bien enregistré les bonnes valeurs
        assertEquals("Renault", voiture.getMarque());
        assertEquals(15000, voiture.getPrix());
        assertEquals(1, voiture.getId());
    }

}
