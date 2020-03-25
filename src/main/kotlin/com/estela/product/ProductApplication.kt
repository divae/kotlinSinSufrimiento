package com.estela.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

	@GetMapping("/{productId}")
	fun findById(@PathVariable productId:String) = productService.findById(productId)
}

interface BasicCrud<T,ID>{
	fun findAll():List<T>
	fun findById(id: ID): T?
	fun save(t:T): Boolean
	fun update(t:T): Boolean
	fun deleteById(id:ID): Boolean
}

@Service
class ProductService: BasicCrud<Product,String> {
	private val products:MutableSet<Product> = mutableSetOf(Product("Apple", 22.2), Product("Banana", 22.3))

	override fun findAll(): List<Product> = products.toList()

	override fun findById(producId: String) = this.products.find {product -> product.name == producId }

	override fun save(product: Product) = this.products.add(product)

	override fun update(product: Product) = this.products.remove(product) && this.products.add(product)

	override fun deleteById(product: String) = this.products.remove(this.findById(product))
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