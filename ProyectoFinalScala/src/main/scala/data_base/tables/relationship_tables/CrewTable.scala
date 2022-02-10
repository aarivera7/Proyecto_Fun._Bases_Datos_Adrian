package data_base.tables.relationship_tables

import model.classes.relationship_classes.Crew
import data_base.TablesQueries.{credits, movies}
import slick.jdbc.MySQLProfile.api._

class CrewTable(tag: Tag) extends Table[Crew](tag, "crew"){
  def idMovie = column[Int]("id_movie")
  def creditId = column[String]("credit_id", O.Length(25))

  override def * = (idMovie, creditId).mapTo[Crew]

  def pk = primaryKey(null, (idMovie, creditId))
  def movie =
    foreignKey("fk_crew_movie", idMovie, movies)(_.idMovie)
  def credit =
    foreignKey("fk_crew_credit", creditId, credits)(_.creditId)
}
