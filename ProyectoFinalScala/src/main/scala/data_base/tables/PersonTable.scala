package data_base.tables

import model.classes.Person
import slick.jdbc.MySQLProfile.api._

class PersonTable(tag: Tag) extends Table[Person](tag, "person"){
  def name = column[String]("name")
  def gender = column[Int]("gender")
  def id = column[Int]("id", O.PrimaryKey)
  override def * = (name, gender, id).mapTo[Person]
}
