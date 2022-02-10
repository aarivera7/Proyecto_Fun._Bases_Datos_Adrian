package data_base.tables.relationship_tables

import model.classes.relationship_classes.Director
import data_base.TablesQueries.credits
import slick.jdbc.MySQLProfile.api._

class DirectorTable (tag: Tag) extends Table[Director](tag, "Director"){
  def idDirector = column[Int]("idDirector", O.PrimaryKey)
  override def * = idDirector.mapTo[Director]

  def person =
    foreignKey("fk_director_credit", idDirector, credits)(_.idPerson)
}
