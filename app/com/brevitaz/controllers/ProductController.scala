package com.brevitaz.controllers

import com.brevitaz.Repository.ProductRepository
import com.brevitaz.models.Product
import javax.inject._
import play.api.libs.json.{JsError, JsValue, Json}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._

@Singleton
class ProductController @Inject()(cc: ControllerComponents,repo: ProductRepository) extends AbstractController(cc) {

  def addProduct = Action(parse.json).async { implicit request:Request[JsValue] =>

    val productRequest = request.body.validate[Product]
    productRequest.fold(
      errors => Future(BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toJson(errors)))),
      product => repo.createProduct(product).map(p => Ok(Json.toJson(p)))
    )
  }

  def getAllProduct = Action.async { implicit request =>
    repo.listAllProduct().map(people => Ok(Json.toJson(people)))
  }

  def getPersonById(id: Long): Action[AnyContent] = Action.async { implicit request =>
    repo.findProductById(id).map {
      case Some(person) => Ok(Json.toJson(person))
      case None => NotFound(Json.obj(
        "status" -> false,
        "message" -> "The product you're looking for, is not in the DB."
      ))
    }
  }
}
