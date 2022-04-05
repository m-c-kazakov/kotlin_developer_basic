package com.example.homework_5.controller

import com.example.homework_5.dto.IdDto
import com.example.homework_5.dto.NameDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
internal class HomeworkControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    fun asJsonString(obj: Any): String {
        return try {
            ObjectMapper().writeValueAsString(obj)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @Test
    fun get() {
        val id = "123"
        val andReturn = mockMvc.perform(
            MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(IdDto(id)))
        ).andExpect(MockMvcResultMatchers.status().isOk).andReturn()

        val result = andReturn.response.contentAsString
        assertEquals(id, result)
    }

    @Test
    fun post() {

        val name = "Boris"
        val andReturn = mockMvc.perform(
            MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(NameDto(name)))
        ).andExpect(MockMvcResultMatchers.status().isOk).andReturn()

        val result = andReturn.response.contentAsString
        assertEquals("{\"greeting\":\"Hello Boris\"}", result)
    }
}