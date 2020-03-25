package com.estela.product.controller

import com.estela.product.domain.Product
import com.estela.product.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/product")
class ProductController(
        private val productService: ProductService
){
    @GetMapping
    fun findAll() = productService.findAll()

    @GetMapping("/{productId}")
    fun findById(@PathVariable productId:String) = productService.findById(productId)

    @PostMapping
    fun save(@RequestBody product: Product) = productService.save(product)

    @PutMapping
    fun update(@RequestBody product: Product){
        productService.update(product)
    }

    @DeleteMapping("/{productId}")
    fun deleteById(@PathVariable productId: String) = productService.deleteById(productId)

}