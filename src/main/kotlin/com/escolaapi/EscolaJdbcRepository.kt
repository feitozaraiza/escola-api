package com.escolaapi

import com.escolaapi.model.view.Escola
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import javax.sql.DataSource

//
@Repository
class EscolaJdbcRepository (@Autowired val jdbcTemplate: JdbcTemplate?) {  // jdbc conversa com o bd

    fun findAll(): MutableList<Escola>? { // mutable é o retorno do jdbctemplate query
        var rowMapper: RowMapper<Escola> = RowMapper<Escola> { resultSet: ResultSet, rowIndex: Int ->
            Escola(resultSet.getInt("id"), resultSet.getString("nome_da_escola"),
                resultSet.getString("cep"),  resultSet.getString("cidade"),
                resultSet.getInt("quantidade_funcionarios"), resultSet.getInt("quantidade_" +
                        "alunos"),
                resultSet.getString("estado"))
        }

        var results = jdbcTemplate?.query("SELECT * FROM Escola", rowMapper)  // rowmapper organiza o resultado do select

        println("r:")
        results?.forEach { rec -> println(rec) }

        return results
    }
}











// data conexao com bd
//// hikiri biblioteca do data source - conexão com bd