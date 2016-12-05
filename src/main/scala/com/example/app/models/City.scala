package com.example.app.models

import com.example.app.{HasIntId, Tables, Updatable}
import slick.driver.H2Driver.api._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by matt on 11/27/16.
  */

case class City(name: String, countryName: String, id: Int = 0) extends HasIntId[City]{
  def updateId(id: Int) = this.copy(id = id)
}

object City extends Updatable[City, (Int, String, String), Tables.Cities]{

  lazy val table = Tables.cities

  def reify(tuple: (Int, String, String)) =
    City(tuple._2, tuple._3, tuple._1)

  def updateQuery(a: City) = table.filter(_.id === a.id)
      .map(x => (x.name, x.country))
      .update((a.name, a.countryName))

  def classToTuple(a: City): (Int, String, String) =
    (a.id, a.name, a.countryName)

}