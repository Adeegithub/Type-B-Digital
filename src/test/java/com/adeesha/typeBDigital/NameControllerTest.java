package com.adeesha.typeBDigital;

import com.adeesha.typeBDigital.controller.NameController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(NameController.class)
public class NameControllerTest {

    private final MockMvc mockMvc;

    @Autowired
    public NameControllerTest(MockMvc mockMvc){
        this.mockMvc = mockMvc;
    }

    // Happy Path
    @Test
    @DisplayName("Should return 200 Ok with 'Hello Adeesha' for name 'adeesha")
    void whenValidNameInFirstHalfThenReturn200Ok() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "adeesha"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Adeesha"));
    }

    // Valid input with All Uppercases
    @Test
    @DisplayName("Should return 200 Ok with 'Hello Adeesha' for name 'ADEESHA")
    void whenValidNameInFirstHalfWithAllUpperCaseThenReturn200Ok() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "ADEESHA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Adeesha"));
    }

    // Valid Input with mixed casing
    @Test
    @DisplayName("Should return 200 OK with 'Hello Donald' for name 'dOnAlD'")
    void whenValidNameWithMixedCaseThenReturn200Ok() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "dOnAlD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Donald"));
    }

    // Valid Input Edge case 'M'
    @Test
    @DisplayName("Should return 200 OK for 'Mahela' (edge case M)")
    void whenValidNameAtEdgeThenReturn200Ok() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "mahela"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Mahela"));
    }

    // Invalid Input Second Half
    @Test
    @DisplayName("Should return 400 Bad Request for name 'Ricky'")
    void whenNameInSecondHalfThenReturn400BadRequest() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "Ricky"))
                .andExpect(status().isBadRequest()) // Check for HTTP 400
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    // Invalid Input Edges Case 'N'
    @Test
    @DisplayName("Should return 400 Bad Request for 'Neymar' (edge case N)")
    void whenNameAtSecondHalfEdgeThenReturn400BadRequest() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "Oscar"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    // Invalid Input -> Empty Name
    @Test
    @DisplayName("Should return 400 Bad Request for an empty name")
    void whenNameIsEmptyThenReturn400BadRequest() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    // Invalid Input -> Blank String
    @Test
    @DisplayName("Should return 400 Bad Request for a blank name")
    void whenNameIsBlankThenReturn400BadRequest() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "   "))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    // Invalid Input -> Null Input
    @Test
    @DisplayName("Should return 400 Bad Request when name param is missing")
    void whenNameParamIsMissingThenReturn400BadRequest() throws Exception {
        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest());
    }

    // Invalid Input -> integer
    @Test
    @DisplayName("Should return 400 Bad Request for a numeric name")
    void whenNameIsNumericThenReturn400BadRequest() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "12345"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    //Invalid Input -> Special Characters
    @Test
    @DisplayName("Should return 400 Bad Request for a special characters name")
    void whenNameIsSpecialCharactersThenReturn400BadRequest() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "!!@$$&"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

}
