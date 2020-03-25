package com.estela.product.domain

data class Product(val name:String,val price:Double?=22.3){
	override fun equals(other: Any?): Boolean {
		other ?: return false
		if(other === this)return true
		if(this.javaClass != other.javaClass) return false
		other as Product

		return this.name == other.name
	}
}