package com.escolaapi

import com.escolaapi.service.EscolaDTOFixture
import com.escolaapi.service.EscolaFixture
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class EscolaIntegrationTest @Autowired constructor(
    val mockMvc: MockMvc, // objeto do spring
    val objectMapper: ObjectMapper
) {
    val baseUrl = "/escola"

    @Nested
    @DisplayName("GET /escola")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetEscolas {
        @Test
        fun ` should return all Escolas` () {
            mockMvc.get(baseUrl)  // é como se fosse no postman, ele vai na portinha e etc
                .andDo { print() }  // imprimi
                .andExpect {  // faz comparação
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].id") {
                        value("6")  // e quando não tiver nenhuma escola? da erro ai isso ta certo?
                    }
                }
        }
    }

    @Nested
    @DisplayName("POST /escola")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewEscola {
        @Test
        @DirtiesContext
        fun `should add new Escola`() {
            val newEscola = EscolaFixture.defaultEscola()
            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newEscola)
            }
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON) //  se a api retorna jason em "headers"
                       jsonPath("estado") {
                            value(newEscola.estado)  // comparando 1 a 1 se o jason criado (body) tem as propriedades q eu mandei (newEscola)
                        }
                        jsonPath("quantidade_funcionarios") {
                            value(newEscola.quantidade_funcionarios)  // comparando 1 a 1 se o jason criado (body) tem as propriedades q eu mandei (newEscola)
                        }
                        jsonPath("quantidade_alunos") {
                            value(newEscola.quantidade_alunos)  // comparando 1 a 1 se o jason criado (body) tem as propriedades q eu mandei (newEscola)
                        }
                        jsonPath("nome_da_escola") {
                            value(newEscola.nome_da_escola)  // comparando 1 a 1 se o jason criado (body) tem as propriedades q eu mandei (newEscola)
                        }
                    }
                }
        }
    }
}