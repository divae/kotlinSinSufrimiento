package com.estela.product

import com.estela.product.domain.Product
import com.estela.product.service.ProductService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductApplicationTests {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@Autowired
	private lateinit var mapper: ObjectMapper

	@Autowired
	private lateinit var productService: ProductService

	@Test
	fun `Get() - returns the list of products if was successful`() {
		val productsFromService = productService.findAll()
		val json = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product"))
				.andExpect(status().isOk)
				.andReturn().response.contentAsString

		val products:List<Product> = mapper.readValue(json)

		assertThat(productsFromService, Matchers.`is`(Matchers.equalTo(products)))
	}

	@Test
	fun `Get() - if not exist returns not found`() {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(status().isNotFound)
				.andReturn().response.contentAsString
	}

}
