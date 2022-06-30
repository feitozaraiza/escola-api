package com.escolaapi.model.view

interface EscolaView {
    val id: Int
    val estado: String
    val quantidade_funcionarios: Int
    val quantidade_alunos: Int
    val nome_da_escola: String
}