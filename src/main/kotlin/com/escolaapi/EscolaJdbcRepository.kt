/*
package com.escolaapi

import com.escolaapi.model.view.Escola
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.Random
import javax.persistence.Id
import javax.sql.DataSource

//
@Repository
class EscolaJdbcRepository(
    @Autowired private val dataSource: DataSource
) {  // jdbc conversa com o bd

    private val jdbcInsert: SimpleJdbcInsert = SimpleJdbcInsert(dataSource)

    private val jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)
    private val namedJdbcTemplate: NamedParameterJdbcTemplate = NamedParameterJdbcTemplate(dataSource)

    companion object {
        private const val INSERT_ESCOLA = """
            INSERT INTO Escola (id, cep, estado, cidade, quantidade_funcionarios, quantidade_alunos, nome_da_escola)
            VALUES (:id, :cep, :estado, :cidade, :quantidade_funcionarios, :quantidade_alunos, :nome_da_escola)
        """
    }

    fun criaRowMapper(): RowMapper<Escola> {
        val rowMapper: RowMapper<Escola> = RowMapper<Escola> { resultSet: ResultSet, _: Int ->
            Escola(
                id = resultSet.getInt("id"),
                cep = resultSet.getString("nome_da_escola"),
                estado = resultSet.getString("cep"),
                cidade = resultSet.getString("cidade"),
                quantidade_funcionarios = resultSet.getInt("quantidade_funcionarios"),
                quantidade_alunos = resultSet.getInt("quantidade_alunos"),
                nome_da_escola = resultSet.getString("estado")
            )
        }

        return rowMapper
    }

    fun findAll(): List<Escola>? { // mutable é o retorno do jdbctemplate query
        val rowMapper: RowMapper<Escola> = criaRowMapper()

        val results = jdbcTemplate.query("SELECT * FROM Escola", rowMapper)  // rowmapper organiza o resultado do select

        println("r:")
        results.forEach { rec -> println(rec) }

        return results
    }

    fun deleteById(id: Int) {
        val sql = "DELETE FROM Escola WHERE id=$id"
        jdbcTemplate.update(sql)
    }

//    fun save(escola: Escola): Escola {
//        // https://www.baeldung.com/spring-jdbc-autogenerated-keys
//
//        val params = MapSqlParameterSource()
//            .addValue("id", Random().nextInt())
//            .addValue("cep", escola.cep)
//            .addValue("nome_da_escola", escola.nome_da_escola)
//            .addValue("estado", escola.estado)
//            .addValue("quantidade_alunos", escola.quantidade_alunos)
//            .addValue("quantidade_funcionarios", escola.quantidade_funcionarios)
//            .addValue("cidade", escola.cidade)
//
////        val paramsMap = mutableMapOf<String, Any>(
////            "id" to escola.id,
////            "cep" to escola.cep,
////            "nome_da_escola" to escola.nome_da_escola,
////            "estado" to escola.estado,
////            "quantidade_alunos" to escola.quantidade_alunos,
////            "quantidade_funcionarios" to escola.quantidade_funcionarios,
////            "cidade" to escola.cidade
////        )
////        val keyHolder: KeyHolder = GeneratedKeyHolder()
//
//        // cep, estado, cidade, quantidade_funcionarios, quantidade_alunos, nome_da_escola
//        val simpleInsert = SimpleJdbcInsert(jdbcTemplate)
//        simpleInsert.withTableName("escola")
//            .usingColumns(
//                "id",
//                "cep",
//                "estado",
//                "cidade",
//                "quantidade_funcionarios",
//                "quantidade_alunos",
//                "nome_da_escola"
//            ).usingGeneratedKeyColumns(
//                "id",
//                "cep",
//                "estado",
//                "cidade",
//                "quantidade_funcionarios",
//                "quantidade_alunos",
//                "nome_da_escola"
//            )
//
//
//        val keyHolder = simpleInsert.executeAndReturnKeyHolder(params)
//
//        //jdbcTemplate.update(sql, keyHolder, arrayOf("id", "cep")) // ta retornando 1, pq ? o proprio update diz q retorna um inteiro
//        // como retornar escola recem criada? pq aqui ele retorna la so ta criando a escola no banco
//        val persistedId = keyHolder.keys?.get("id")
//        println(persistedId)
//        return escola
//    }
}


// testar falha



// data conexao com bd
//// hikiri biblioteca do data source - conexão com bd*/
