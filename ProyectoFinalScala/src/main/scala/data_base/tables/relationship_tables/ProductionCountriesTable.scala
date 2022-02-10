package data_base.tables.relationship_tables

import model.classes.relationship_classes.ProductionCountries
import data_base.TablesQueries.{countries, movies}
import slick.jdbc.MySQLProfile.api._

class ProductionCountriesTable (tag: Tag) extends Table[ProductionCountries](tag, "production_countries"){
  def idMovie = column[Int]("idMovie")
  def iso_3166_1 = column[String]("iso31661", O.Length(2))

  override def * = (idMovie, iso_3166_1).mapTo[ProductionCountries]

  def pk = primaryKey(null, (idMovie, iso_3166_1))
  def movie =
    foreignKey("fk_production_countries_movie", idMovie, movies)(_.idMovie)
  def country =
    foreignKey("fk_production_countries_country", iso_3166_1, countries)(_.iso_3166_1,
      onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
}
