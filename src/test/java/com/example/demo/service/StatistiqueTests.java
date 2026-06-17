package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StatistiqueTests {

    // Cas 1 : Aucune voiture (Déclenchement d'une exception)
    @Test
    void testMatriceZeroVoiture() {
        StatistiqueImpl statistique = new StatistiqueImpl();
        
        // On vérifie qu'une ArithmeticException est bien levée quand on fait prixMoyen()
        assertThrows(ArithmeticException.class, () -> {
            statistique.prixMoyen();
        });
    }

    // Cas 2 : Une seule voiture
    @Test
    void testMatriceUneVoiture() {
        StatistiqueImpl statistique = new StatistiqueImpl();
        Voiture v1 = mock(Voiture.class);
        when(v1.getPrix()).thenReturn(15000);
        statistique.ajouter(v1);

        Echantillon resultat = statistique.prixMoyen();
        assertEquals(1, resultat.getNombreDeVoitures());
        assertEquals(15000, resultat.getPrixMoyen());
    }

    // Cas 3 : Plusieurs voitures (Cas classique)
    @Test
    void testMatricePlusieursVoitures() {
        StatistiqueImpl statistique = new StatistiqueImpl();
        Voiture v1 = mock(Voiture.class);
        when(v1.getPrix()).thenReturn(10000);
        Voiture v2 = mock(Voiture.class);
        when(v2.getPrix()).thenReturn(20000);
        statistique.ajouter(v1);
        statistique.ajouter(v2);

        Echantillon resultat = statistique.prixMoyen();
        assertEquals(2, resultat.getNombreDeVoitures());
        assertEquals(15000, resultat.getPrixMoyen());
    }

    // Cas 4 : Véhicule avec un prix de 0
    @Test
    void testMatriceVoiturePrixZero() {
        StatistiqueImpl statistique = new StatistiqueImpl();
        Voiture v1 = mock(Voiture.class);
        when(v1.getPrix()).thenReturn(0);
        Voiture v2 = mock(Voiture.class);
        when(v2.getPrix()).thenReturn(20000);
        statistique.ajouter(v1);
        statistique.ajouter(v2);

        Echantillon resultat = statistique.prixMoyen();
        assertEquals(2, resultat.getNombreDeVoitures());
        assertEquals(10000, resultat.getPrixMoyen()); // (0 + 20000) / 2 = 10000
    }
}