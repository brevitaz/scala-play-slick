package com.brevitaz.models

import play.api.libs.json.Json

case class Product(id: Long, title: String, description: String, rating: Long, price: Double)

object Product {
  implicit val personFormat = Json.format[Product]
}