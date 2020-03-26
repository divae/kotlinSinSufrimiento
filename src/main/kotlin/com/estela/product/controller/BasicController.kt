package com.estela.product.controller
import com.estela.product.service.BasicCrud
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

abstract class BasicController<T,ID>(
        private val basicCrud: BasicCrud<T,ID>
) {
    @GetMapping
    fun findAll() = basicCrud.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: ID): ResponseEntity<T> {
        val entity = basicCrud.findById(id)
        return ResponseEntity.status(if(entity!=null) HttpStatus.OK else HttpStatus.NO_CONTENT).body(entity)
    }

    @PostMapping
    fun save(@RequestBody t: T) = basicCrud.save(t)

    @PutMapping
    fun update(@RequestBody t: T) = basicCrud.update(t)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: ID) = basicCrud.deleteById(id)
}