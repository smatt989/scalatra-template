package com.example.app

import com.example.app.models._
import org.joda.time.Duration

import scala.concurrent.Future
import Tables._
import slick.driver.H2Driver.api._

import scala.concurrent.ExecutionContext.Implicits.global

object DataImport {

  def populateData(db: Database) = {
    val cities = Seq(City("beijing", "china"), City("new-york-city", "usa"), City("chicago", "usa"), City("san-francisco", "usa"))

    val city = cities.map(c => db.run(getCity(c.name, c.countryName)).map(_.map(c => City.reify(c))).flatMap{
      case Some(k) => Future.apply(k)
      case None => City.save(c)
    })
  }

  def getCity(cityName: String, countryName: String) = {
    cities.filter(c => c.name === cityName && c.country === countryName).result.headOption
  }

}
