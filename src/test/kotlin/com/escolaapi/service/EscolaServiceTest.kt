package com.escolaapi.service

import com.escolaapi.dto.EscolaDTO
import com.escolaapi.mapper.EscolaViewMapper
import com.escolaapi.model.view.Escola
import com.escolaapi.repository.EscolaRepository
import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import java.util.Optional

// o metodo create escola recebe uma escola como parametro e retorna uma escolaDTO
// e ai ele salva uma escola no banco de dados (repository)
// ai ele pega essa escola salva e usa o map pra criar uma DTO

internal class EscolaServiceTest {
    private val repository: EscolaRepository = mockk()
    private val escolaViewMapper: EscolaViewMapper = mockk()
    private val escolaService = EscolaService(repository, escolaViewMapper)

    @Test
    fun ` Should Save Escola and Return EscolaDTO `() {
        val escolaMock = mockk<Escola> {   // mocka objeto de dados     criando um mock da escola
            every { id } returns 1
            every {nome_da_escola} returns "Salesiano"
            every {estado} returns "PE"
            every { quantidade_alunos } returns 50
            every { quantidade_funcionarios } returns 150
        }

        val escolaSalva = mockk<Escola>{  // criando um mock da escola salva, pra gente simular ali no save que ele retornou a escola salva, ou seja um outro objeto
            every { id } returns 1
            every {nome_da_escola} returns "Salesiano"
            every {estado} returns "PE"
            every { quantidade_alunos } returns 50
            every { quantidade_funcionarios } returns 150
        }

        every { repository.save(escolaMock) } returns escolaSalva // ai coloquei o metodo save, recebendo a escolamock pra retornar a escolaSalva

        val escolaDTO = mockk<EscolaDTO>{  // criando um mock da escola DTO,
            every { id } returns 1
            every {nome_da_escola} returns "Salesiano"
            every {estado} returns "PE"
            every { quantidade_alunos } returns 50
            every { quantidade_funcionarios } returns 150
        }

        every {escolaViewMapper.map(escolaSalva) } returns escolaDTO  // e aqui to usando o map, que transforma a escola em DTO com o parametro da escolaSalva, retornando o mock da EscolaDTO

        val escolaDTOFromService = escolaService.createEscola(escolaMock)  // fazendo as comparações pq na realidade preciso que os dados da DTO coincidam com os dados da Escola
        assertEquals(escolaDTOFromService.nome_da_escola, escolaMock.nome_da_escola)
        assertEquals(escolaDTOFromService.estado, escolaMock.estado)
        assertEquals(escolaDTOFromService.quantidade_alunos, escolaMock.quantidade_alunos)
        assertEquals(escolaDTOFromService.quantidade_funcionarios, escolaMock.quantidade_funcionarios)

    }

    @Test
    fun ` Should Delete Escola By ID ` () {
        val escolaMock = mockk<Escola> { // criei um mock da escola
            every { id } returns 1
            every {nome_da_escola} returns "Salesiano"
            every {estado} returns "PE"
            every { quantidade_alunos } returns 50
            every { quantidade_funcionarios } returns 150
        }

        // every { repository.deleteById(1) } just runs  MESMO EFEITO
        justRun { repository.deleteById(1) } //just run serve pra dizer ao mock "qnd chamar esse metodo, tu faz nd" coloquei isso pq delete é um void
        escolaService.deletar(1) // chamando o service q a gente ta testando com o metodo que a gente quer, so q ele é void, unica coisa pra testar é se chama o delete by id
        verify { repository.deleteById(1) } // verifica se chamou o delete by id do mock
    }

    @Test
    fun ` Should Update the fields QuantidadeDeAlunos and QuantidadeDeFuncionarios of EscolaDTO ` () {

        val escolaUpdateDTO = EscolaUpdateDTOFixture.defaultEscolaUpdateDTO()

        val escolaDTO = EscolaDTOFixture.defaultEscolaDTO()

        val escola = EscolaFixture.defaultEscola()

        every {repository.findById(1)} returns Optional.of(escola)       // pacotinho que pode carregar um objeto ou pode ta vazio, precisei criar pq meu findbyid retorna um optional ai descobri esse .of pra isso
        every {escolaViewMapper.map(escola) } returns escolaDTO  // definindo o comportamento do view mapper

        val escolaUpdateFinal = escolaService.updateEscola(escolaUpdateDTO)  // chamando o metodo
        assertEquals(escolaUpdateFinal.quantidade_alunos, escolaUpdateDTO.quantidade_alunos)
        assertEquals(escolaUpdateFinal.quantidade_funcionarios, escolaUpdateDTO.quantidade_funcionarios)
    }

    @Test
    fun ` Should List All Escola ` () {
        val escola = EscolaFixture.defaultEscola()
        val escolaDTO = EscolaDTOFixture.defaultEscolaDTO()
        val pageable = PageableFixture.defaultPageable()
        val listaEscolas = listOf(escola)
        val pageEscolasFromRepository = PageImpl(listaEscolas); // pageimpl pega a lista e transforma em page

        every { repository.findAll(pageable) } returns pageEscolasFromRepository
        every { escolaViewMapper.map(escola) } returns escolaDTO

        val pagesEscolasViews = escolaService.listAllEscola("todas", pageable)

        assertEquals(pagesEscolasViews.totalElements, pageEscolasFromRepository.totalElements)
        assertEquals(pagesEscolasViews.first().id, pageEscolasFromRepository.first().id)
    }
}

