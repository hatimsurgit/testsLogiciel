package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StatistiqueTests {

    @Test
    void testPrixMoyenAvecMockito() {
        // On instancie la classe que l'on veut tester
        StatistiqueImpl statistique = new StatistiqueImpl();

        // On crée de fausses voitures avec Mockito (des "mocks")
        Voiture voiture1 = mock(Voiture.class);
        when(voiture1.getPrix()).thenReturn(10000);

        Voiture voiture2 = mock(Voiture.class);
        when(voiture2.getPrix()).thenReturn(20000);

        // On ajoute ces fausses voitures dans les statistiques
        statistique.ajouter(voiture1);
        statistique.ajouter(voiture2);

        // On appelle la méthode à tester
        Echantillon resultat = statistique.prixMoyen();

        // On vérifie que la moyenne est correcte
        assertEquals(2, resultat.getNombreDeVoitures());
        assertEquals(15000, resultat.getPrixMoyen());
    }
}