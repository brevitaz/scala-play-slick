package com.brevitaz.Repository

import com.brevitaz.models.Product
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class ProductRepository @Inject()( dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

    private val dbConfig = dbConfigProvider.get[JdbcProfile]
    import dbConfig._
    import profile.api._

    private class ProductTable(tag: Tag) extends Table[Product](tag, "product") {

      def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

      def title = column[String]("title")

      def description = column[String]("description")

      def rating = column[Long]("rating")

      def price = column[Double]("price")

      def * = (id, title, description, rating, price) <> ((Product.apply _).tupled, Product.unapply)
    }

    private val products = TableQuery[ProductTable]

    def createProduct(product: Product) = db.run {
      (products.map(p => (p.title, p.description,p.rating,p.price))
        returning products.map(_.id)
        into ((tpl, id) => Product(id, tpl._1, tpl._2,tpl._3,tpl._4))
        ) += (product.title, product.description,product.rating, product.price)
    }

    def listAllProduct():Future[Seq[Product]] = db.run {
      products.result
    }

    def findProductById(id:Long) = db.run {
      products.filter(_.id === id).result.headOption
    }
}
