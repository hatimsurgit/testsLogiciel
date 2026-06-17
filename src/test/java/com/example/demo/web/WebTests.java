package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.StatistiqueImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebTests {

    // On mock la logique métier pour ne tester que le contrôleur web
    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetStatistiques() throws Exception {
        // 1. On définit le comportement simulé du mock
        when(statistiqueImpl.prixMoyen()).thenReturn(new Echantillon(3, 12000));

        // 2. On simule une requête HTTP de type GET sur "/statistique"
        mockMvc.perform(get("/statistique"))
               .andExpect(status().isOk()) // On s'attend à un code 200 (OK)
               .andExpect(jsonPath("$.nombreDeVoitures").value(3)) // On vérifie le JSON renvoyé
               .andExpect(jsonPath("$.prixMoyen").value(12000));
    }

    @Test
    void testCreerVoiture() throws Exception {
        // 1. On simule une requête HTTP de type POST sur "/voiture" avec une voiture au format JSON
        mockMvc.perform(post("/voiture")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"marque\":\"Peugeot\",\"prix\":15000}"))
               .andExpect(status().isOk()); // On s'attend à un code 200 (OK)

        // 2. On vérifie que la méthode ajouter() a bien été déclenchée côté serveur
        verify(statistiqueImpl, times(1)).ajouter(any(Voiture.class));
    }
}
