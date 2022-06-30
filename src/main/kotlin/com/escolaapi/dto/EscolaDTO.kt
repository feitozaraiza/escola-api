package com.escolaapi.dto

import com.escolaapi.model.view.EscolaView


class EscolaDTO(
    override val id: Int,
    override val estado: String,
    override val quantidade_funcionarios: Int,
    override val quantidade_alunos: Int,
    override val nome_da_escola: String
) : EscolaView


// aqui a gente controla o q é para mostrar


// view
// blindando o dominio, so mostra o que tem q mostrar pra quem precisa