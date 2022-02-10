package data_base.tables.relationship_tables

import model.classes.relationship_classes.SpokenLanguages
import data_base.TablesQueries.{languages, movies}
import slick.jdbc.MySQLProfile.api._

class SpokenLanguagesTable (tag: Tag) extends Table[SpokenLanguages](tag, "spoken_languages") {
  def idMovie = column[Int]("idMovie")

  def iso_639_1 = column[String]("iso_639_1", O.Length(2))

  override def * = (idMovie, iso_639_1).mapTo[SpokenLanguages]

  def pk = primaryKey(null, (idMovie, iso_639_1))

  def movie =
    foreignKey("fk_spoken_languages_movie", idMovie, movies)(_.idMovie)

  def language =
    foreignKey("fk_spoken_languages_language", iso_639_1, languages)(_.iso_639_1,
      onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
}
