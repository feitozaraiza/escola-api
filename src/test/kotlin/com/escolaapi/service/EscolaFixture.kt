package com.escolaapi.service

import com.escolaapi.dto.EscolaDTO
import com.escolaapi.dto.EscolaUpdateDTO
import com.escolaapi.model.view.Escola

object EscolaFixture {

    fun defaultEscola (): Escola {
        return Escola(
            id = 1,
            nome_da_escola = "Salesiano",
            estado = "PE",
            quantidade_alunos = 50,
            quantidade_funcionarios = 150,
            cep = "50741390",
            cidade = "Recife"
        )
    }
}

// um fixture pra cada objeto