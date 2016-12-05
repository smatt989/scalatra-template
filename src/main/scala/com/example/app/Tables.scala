package com.example.app

import java.sql.Timestamp
import slick.driver.H2Driver.api._


object Tables {


  class Cities(tag: Tag) extends Table[(Int, String, String)](tag, "CITIES") with HasIdColumn[Int] {
    def id = column[Int]("CITY_ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def country = column[String]("COUNTRY")

    def * = (id, name, country)
  }

  val cities = TableQuery[Cities]

  val schemas = (cities.schema)


  // DBIO Action which creates the schema
  val createSchemaAction = schemas.create

  // DBIO Action which drops the schema
  val dropSchemaAction = schemas.drop

  // Create database, composing create schema and insert sample data actions
  val createDatabase = DBIO.seq(createSchemaAction)

}

trait HasIdColumn[A]{
  def id: Rep[A]
}
