package com.escolaapi.mapper

import com.escolaapi.dto.EscolaDTO
import com.escolaapi.model.view.Escola
import org.springframework.stereotype.Component

@Component
class EscolaViewMapper: Mapper<Escola, EscolaDTO> { // converter  escolaviewmapper implementa a interface mapper

    override fun map(t: Escola): EscolaDTO {
        return EscolaDTO(
            id = t.id,
            estado = t.estado,
            quantidade_funcionarios = t.quantidade_funcionarios,
            quantidade_alunos = t.quantidade_alunos,
            nome_da_escola = t.nome_da_escola
       )
    }
}

