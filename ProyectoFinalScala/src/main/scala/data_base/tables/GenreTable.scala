package data_base.tables

import model.classes.Genre
import slick.jdbc.MySQLProfile.api._

class GenreTable (tag: Tag) extends Table[Genre](tag, "Genre"){
  def idGenre = column[Int]("idGenre", O.PrimaryKey)
  def name = column[String]("name")
  override def * = (idGenre, name).mapTo[Genre]
}
