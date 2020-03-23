package com.estela.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class ProductApplication

fun main(args: Array<String>) {
	runApplication<ProductApplication>(*args)
}

@RestController
@RequestMapping("/api/v1/product")
class productController(
		private val productService: ProductService
){
	@GetMapping
	fun findAll() = productService.findAll()
}

@Service
class ProductService{
	private val products:Set<Product> = setOf(Product("Apple", 22.2), Product("Banana", 22.3))

	fun findAll():List<Product> = products.toList()
}

data class Product(val name:String,val price:Double?=22.3){
	override fun equals(other: Any?): Boolean {
		other ?: return false
		if(other === this)return true
		if(this.javaClass != other.javaClass) return false
		other as Product

		return this.name == other.name
	}
}