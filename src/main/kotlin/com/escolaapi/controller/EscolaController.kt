package com.escolaapi.controller    // o nome pode ser controller ou Route , recebe requisiçõs.

import com.escolaapi.dto.EscolaDTO
import com.escolaapi.dto.EscolaUpdateDTO
import com.escolaapi.model.view.Escola
import com.escolaapi.model.view.EscolaView
import com.escolaapi.service.EscolaService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional


// mapeia os end points : ex: /escola

@RestController   // anotação de controller
@RequestMapping("/escola") // anotação que indica que toda vez que eu for para o controller eu tenho que passar aqui   END POINT
class EscolaController(
    private val escolaService: EscolaService
){


    @GetMapping
    fun listAllEscola(
        @RequestParam(required = false) nome_da_escola: String?,  // solicitação de parametro no get
        @PageableDefault(size = 5) page: Pageable   // limitando o q aparece
    ): Page<EscolaView> {
        return escolaService.listAllEscola(nome_da_escola,page)  // service onde vai executar a regra do negocio
    }

    @PostMapping // requisição do tipo POST
    @Transactional  // comunicação no banco de dados
    @CacheEvict (value = ["escola"], allEntries = true)// evita cache
    fun createEscola(
        @RequestBody form: Escola,  // solicitando um corpo para a requisição
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<EscolaDTO> {      // se der certo ele manda um OK
        val escolaDTO = escolaService.createEscola(form)
        val uri = uriBuilder.path("/escola/${escolaDTO.id}").build().toUri()   // uri é onde a url bate, endereço completo, path é as barrinhas, onde bate o endpoint. build faz a construção
        return ResponseEntity.created(uri).body(escolaDTO)
    }

    @PutMapping
    @Transactional  // comunicação no banco de dados
    @CacheEvict (value = ["escola"], allEntries = true)// evita cache
    fun updateEscola(
        @RequestBody form: EscolaUpdateDTO
    ): ResponseEntity<EscolaDTO>{
        val escolaDTO = escolaService.updateEscola(form)
        return ResponseEntity.ok(escolaDTO)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // se der certo n retorna nada
    @Transactional
    @CacheEvict(value = ["escola"], allEntries = true)
    fun deleteEscola(@PathVariable id: Int){ // variavel de caminho é o path variable
        escolaService.deletar(id)
    }


}

// teste