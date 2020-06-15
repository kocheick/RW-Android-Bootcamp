package model.caffe

import java.util.*

// TODO add data, such as ID, list of products, and maybe an optional set of cats adopted/sponsored!
data class Receipt(val id: String = UUID.randomUUID().toString(), var items: List<Product>, var totalPrice: Double, val customerId: String) {

}