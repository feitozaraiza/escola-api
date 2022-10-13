package com.escolaapi.model.view

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name="escola")
data class Escola(
    @NotNull @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  // gerar id sequenciais
    val id: Int,
    val cep: String,
    val estado: String,
    val cidade: String,
    @NotNull
    var quantidade_funcionarios: Int,
    @NotNull
    var quantidade_alunos: Int,
    @NotBlank (message = "nome da escola não pode esta em branco")
    val nome_da_escola: String
)


// no model aqui a gente coloca o q tem na tabela