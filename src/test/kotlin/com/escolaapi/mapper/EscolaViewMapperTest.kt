package com.escolaapi.mapper

import com.escolaapi.dto.EscolaDTO
import com.escolaapi.model.view.Escola
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito

internal class EscolaViewMapperTest {    // VISIBILIDADE : ESTUDAR

    @Test
    fun `should Return EscolaDTO When Receive Escola`() { // `transforma em string` > OPERADOR ESPECIAL : ESTUDAR
        val escolaViewMapper = EscolaViewMapper()  // testando a classe mapper
       // val escola = Escola(1 ,"0585479782", "PE", "Recife", 50, 100, "Salesiano")  // testando o metodo map, pq o map pede uma escola como parametro

        val escolaMock = mockk<Escola> {   // mocka objeto de dados
            every { id } returns 1
            every {nome_da_escola} returns "Salesiano"
            every {estado} returns "PE"
            every { quantidade_alunos } returns 50
            every { quantidade_funcionarios } returns 150
        }

        val escolaDto = escolaViewMapper.map(escolaMock)  // chamei o metodo map e passei a escola como parametro pra saber se ta retornando certo
        assertEquals(escolaMock.id, escolaDto.id)
        assertEquals(escolaMock.nome_da_escola, escolaDto.nome_da_escola)
        assertEquals(escolaMock.estado, escolaDto.estado)
        assertEquals(escolaMock.quantidade_funcionarios, escolaDto.quantidade_funcionarios)
        assertEquals(escolaMock.quantidade_alunos, escolaDto.quantidade_alunos)

    }

}