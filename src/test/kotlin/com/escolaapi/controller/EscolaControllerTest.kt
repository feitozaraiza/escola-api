package com.escolaapi.controller

import com.escolaapi.model.view.Escola
import com.escolaapi.model.view.EscolaView
import com.escolaapi.service.*
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.util.UriComponentsBuilder

internal class EscolaControllerTest {
    private val escolaServiceMock: EscolaService = mockk()  // : é tipo
    private val escolaController = EscolaController(escolaServiceMock) // = atribuindo valor objeto

/*    @Test
    fun ` Should List All Escola `() {
        val pageable = PageableFixture.defaultPageable() // criando parametro
        val nomeEscola = "salesiano" // criando parametro

        val escolaDTO = EscolaDTOFixture.defaultEscolaDTO() // criando a escola pra por dentro da lista
        val listaEscolas = listOf(escolaDTO) // lista da escola
        val pageEscolaView: Page<EscolaView> =
            PageImpl(listaEscolas) // descobri que o pageimpl recebe uma lista como parametro e retorna o page q é o que eu queria, pq eu n consegui criar um page de outra forma
        every {
            escolaServiceMock.listAllEscola(
                nomeEscola,
                pageable
            )
        } returns pageEscolaView // aqui a gente define o q o mock vai retornar que é o page criado

        val escolasFromController = escolaController.listAllEscola(nomeEscola, pageable) // chamando o metodo

        assertEquals(pageEscolaView, escolasFromController)
        assertEquals(pageEscolaView.totalElements, escolasFromController.totalElements)
        assertEquals(pageEscolaView.first(), escolasFromController.first())
    }*/

    @Test   // monta cenario (criando parametros) --- preparando os mocks (every e as val necessarias)  --- chama o metodo --- compara os resultados
    fun ` Should Create Escola `() {
        val escola = EscolaFixture.defaultEscola()  // parametros do metodo Create  = escola
        val uriBuilder = UriComponentsBuilder.newInstance()  // parametros do metodo Create  = uri

        val escolaDTO = EscolaDTOFixture.defaultEscolaDTO() // o createEscola retorna um DTO
        every { escolaServiceMock.createEscola(escola) } returns escolaDTO  // aqui o mock retorna exatamente ele

        val escolaCreated = escolaController.createEscola(escola, uriBuilder)  // aqui chamei o controller e o método

        val uriExpected = UriComponentsBuilder.newInstance().path("/escola/${escolaDTO.id}").build()
            .toUri() // criando uri usando o uri builder pq ele q cria
        val escolaCreatedExpected =
            ResponseEntity.created(uriExpected).body(escolaDTO) // usando a uri q criei ai em cima
        assertEquals(escolaCreated, escolaCreatedExpected)

    }

    @Test
    fun ` Should Update Escola `() {
        val escolaUpdateDTO = EscolaUpdateDTOFixture.defaultEscolaUpdateDTO() // parametros

        val escolaDTO = EscolaDTOFixture.defaultEscolaDTO()
        every { escolaServiceMock.updateEscola(escolaUpdateDTO) } returns escolaDTO  // definindo os comportamentos do mock

        val escolaUpdateFromController = escolaController.updateEscola(escolaUpdateDTO) // chamando o service

        val responseEntityExpected = ResponseEntity.ok(escolaDTO) // comaparando
        assertEquals(escolaUpdateFromController,responseEntityExpected)
        assertEquals(escolaUpdateFromController.statusCode, responseEntityExpected.statusCode)

    }

    @Test
    fun ` Should delete Escola By ID`() {
        val idEscola = 1  // criando a id

        justRun { escolaServiceMock.deletar(idEscola) }  // justruin pq o deletar é void

        escolaController.deleteEscola(idEscola) // chama o metodo sem val pq ele é void não precisar guardar o resultado

        verify { escolaServiceMock.deletar(idEscola) } // verifica se chamou o delete by id do mock
    }
}
