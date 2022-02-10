package data_base.tables

import model.classes.Country
import slick.jdbc.MySQLProfile.api._

class CountriesTable(tag: Tag) extends Table[Country](tag, "country"){
  def iso_3166_1 = column[String]("iso_3166_1", O.PrimaryKey, O.Length(2))
  def name = column[String]("name")
  override def * = (iso_3166_1, name).mapTo[Country]
}
