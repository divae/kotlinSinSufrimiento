package com.estela.product.controller

import com.estela.product.domain.Product
import com.estela.product.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/product")
class ProductController(productService: ProductService):BasicController<Product,String>(productService)