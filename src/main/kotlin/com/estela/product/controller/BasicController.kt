package com.estela.product.controller

import com.estela.product.service.BasicCrud
import org.springframework.web.bind.annotation.*

abstract class BasicController<T,ID>(
        private val basicCrud: BasicCrud<T,ID>
) {
    @GetMapping
    fun findAll() = basicCrud.findAll()

    @GetMapping("/{productId}")
    fun findById(@PathVariable id: ID) = basicCrud.findById(id)

    @PostMapping
    fun save(@RequestBody t: T) = basicCrud.save(t)

    @PutMapping
    fun update(@RequestBody t: T) = basicCrud.update(t)

    @DeleteMapping("/{productId}")
    fun deleteById(@PathVariable id: ID) = basicCrud.deleteById(id)
}