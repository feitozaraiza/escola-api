package com.escolaapi.service

import org.springframework.data.domain.Pageable

object PageableFixture {

    fun defaultPageable (): Pageable {
        return Pageable.unpaged();
    }
}