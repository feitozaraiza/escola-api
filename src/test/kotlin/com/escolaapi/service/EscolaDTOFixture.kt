package com.escolaapi.service

import com.escolaapi.dto.EscolaDTO
import com.escolaapi.dto.EscolaUpdateDTO
import com.escolaapi.model.view.Escola
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

object EscolaDTOFixture {

    fun defaultEscolaDTO(): EscolaDTO {
        return EscolaDTO(  // criando um mock da escola DTO,
            id = 1,
            nome_da_escola = "Salesiano",
            estado = "PE",
            quantidade_alunos = 50,
            quantidade_funcionarios = 150
        )
    }
}

// um fixture pra cada objeto





