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
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class ProductApplicationTests {

	@Autowired
	private lateinit var webApplicationContext: WebApplicationContext

	private val mockMvc: MockMvc by lazy {
		MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print()).build()
	}

	@Autowired
	private lateinit var mapper: ObjectMapper

	@Autowired
	private lateinit var productService: ProductService

	private val URL = "/api/v1/product/"

	@Test
	fun `Get() - returns the list of products if was successful`() {
		val productsFromService = productService.findAll()
		val products:List<Product> = mockMvc.perform(MockMvcRequestBuilders.get(URL))
				.andExpect(status().isOk)
				.bodyTo(mapper)

		assertThat(productsFromService, Matchers.`is`(Matchers.equalTo(products)))
	}

	@Test
	fun `findById() - returns product by id`(){
		val productsFromService = productService.findAll()
		assert(!productsFromService.isEmpty()){"Should not be empty"}
		val product = productsFromService.first()

		mockMvc.perform(MockMvcRequestBuilders.get("${URL}${product.name}"))
				.andExpect(status().isOk)
				.andExpect(jsonPath("$.name", Matchers.`is`(product.name)))
	}

	@Test
	fun `findById() - returns not exist if have error`(){
		mockMvc.perform(MockMvcRequestBuilders.get("${UUID.randomUUID()}"))
				.andExpect(status().isNotFound)
				.andExpect(jsonPath("$").doesNotExist())
	}
}
