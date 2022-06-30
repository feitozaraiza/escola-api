package com.escolaapi.repository

import com.escolaapi.model.view.Escola
import org.springframework.data.jpa.repository.JpaRepository

interface EscolaRepository : JpaRepository<Escola, Int> {  // chamou nossa classe


// jpa pega o que vai ser usado do controller e service e conectar ao banco, atraves dos posts e etc...
}

//trocar jpa para jdbc template


// DOM