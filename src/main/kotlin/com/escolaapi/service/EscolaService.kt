package com.escolaapi.service

import com.escolaapi.dto.EscolaDTO
import com.escolaapi.dto.EscolaUpdateDTO
import com.escolaapi.exception.NotFoundException
import com.escolaapi.mapper.EscolaViewMapper
import com.escolaapi.model.view.Escola
import com.escolaapi.model.view.EscolaView
import com.escolaapi.repository.EscolaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class EscolaService (
    private val repository: EscolaRepository,
    private val escolaViewMapper: EscolaViewMapper,
    private val notFoundExceptionMessage: String = "Escola não encontrada"

) {
    fun listAllEscola(
        nome_da_escola: String?,
        pageable: Pageable
    ):  Page<EscolaView>{  // tipo generico, operador diamante, pode colocar tudo e ele assume valor
        val listEscola = repository.findAll(pageable)   // page é uma coleção

        return listEscola.map { escolaViewMapper.map(it) }  // map vai mapear
    }

    fun createEscola(form: Escola): EscolaDTO {
        val createEscola = repository.save(form)

        return escolaViewMapper.map(createEscola)
    }

    fun updateEscola(form: EscolaUpdateDTO): EscolaDTO {
        val updateEscola = repository.findById(form.id).orElseThrow { NotFoundException(notFoundExceptionMessage) }
        updateEscola.quantidade_alunos = form.quantidade_alunos
        updateEscola.quantidade_funcionarios = form.quantidade_funcionarios

        return escolaViewMapper.map(updateEscola)
    }

    fun deletar(id: Int) {
        repository.deleteById(id)
    }

    fun listAllEscolaofPernambuco(): List<Escola> {
        val listEscola = repository.findAll()
        val listEscolasFiltradas =  listEscola.filter { it.estado == "pernambuco" }

        return listEscolasFiltradas
    }

}

// camada BUSINESS , tem as regraas de negocio

// teste
