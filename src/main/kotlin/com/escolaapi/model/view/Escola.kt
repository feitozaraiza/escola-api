package com.escolaapi.model.view

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="escola")
data class Escola(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  // gerar id sequenciais
    val id: Int,
    val cep: String,
    val estado: String,
    val cidade: String,
    var quantidade_funcionarios: Int,
    var quantidade_alunos: Int,
    val nome_da_escola: String
)


// no model aqui a gente coloca o q tem na tabela