package com.estela.product.service

import com.estela.product.domain.Product
import org.springframework.stereotype.Service
import utils.update

@Service
class ProductService: BasicCrud<Product, String> {
	private val products:MutableSet<Product> = mutableSetOf(Product("Apple", 22.2), Product("Banana", 22.3))

	override fun findAll(): List<Product> = products.toList()

	override fun findById(producId: String) = this.products.find {product -> product.name == producId }

	override fun save(product: Product) = this.products.add(product)

	override fun update(product: Product) = this.products.update(product)

	override fun deleteById(productName: String) = this.products.remove(this.findById(productName))
}