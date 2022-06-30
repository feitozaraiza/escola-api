package com.escolaapi

import com.escolaapi.model.view.Escola
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

@SpringBootApplication
class EscolaApiApplication

fun main(args: Array<String>) {
	runApplication<EscolaApiApplication>(*args)
}