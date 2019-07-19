# Play Slick demo
This application demonstrates implementation of Slick with Play
framework and Scala.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites


- Java v8+

- MySql

- sbt build-tool



## Build and run the project

To build and run the project:

1. Make sure you have set all valid database related properties in
   `conf/application.conf`.

2. Use a command window to change into the example project directory,
   for example: `cd play-mysql-slick-scala`

3. Build the project. Enter: `sbt run`. The project builds and starts
   the embedded HTTP server. Since this downloads libraries and
   dependencies, the amount of time required depends partly on your
   connection's speed.

4. After the message `Server started, ...` displays, enter the following
  URL in a browser: <http://localhost:9000>

## APIs

##### 1 Add product
```http
POST /api/product/add
```

Request format: 
```javascript
{
    "id": Long,
    "title": String,
    "description": String,
    "rating": Long,
    "price": Double   
}
```

##### 2 search product
```http
GET /api/products
```

##### 3 Find Product

```http
GET /api/product/id/{product-id}
```

## Built With

* [Scala-2.13](https://docs.scala-lang.org/) - Language
* [Play Framework-2.7](https://www.playframework.com/documentation/2.7.x/ScalaHome)
  \- The web framework used
* [Sbt](https://www.scala-sbt.org/documentation.html) - Dependency
  Management
* [Slick](http://slick.lightbend.com/doc/3.3.1/) - Functional Relational Mapping (FRM) library for Scala
