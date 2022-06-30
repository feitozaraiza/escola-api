package com.escolaapi.mapper

interface Mapper<T, U> { // genericos

    fun map(t: T): U
}




// se precisar criar outros maps ja usssa essa estrutura da interface