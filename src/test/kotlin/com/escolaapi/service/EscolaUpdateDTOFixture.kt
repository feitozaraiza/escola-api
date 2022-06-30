package com.escolaapi.service

import com.escolaapi.dto.EscolaDTO
import com.escolaapi.dto.EscolaUpdateDTO
import com.escolaapi.model.view.Escola

object EscolaUpdateDTOFixture {

    fun defaultEscolaUpdateDTO(): EscolaUpdateDTO {
        return EscolaUpdateDTO(
            id = 1,
            quantidade_alunos = 50,
            quantidade_funcionarios = 150
        )
    }
}

// um fixture pra cada objeto