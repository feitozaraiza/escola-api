package com.escolaapi

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class EscolaJdbcRepositoryTest { // utilizando para testar os sqls

    @Autowired
    lateinit var escolaJdbcRepository: EscolaJdbcRepository

    @Test
    fun `jdbc test`() {
        escolaJdbcRepository.findAll();
    }
}