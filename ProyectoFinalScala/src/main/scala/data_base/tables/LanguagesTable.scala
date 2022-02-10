package data_base.tables

import model.classes.Languages
import slick.jdbc.MySQLProfile.api._

class LanguagesTable(tag: Tag) extends Table[Languages](tag, "language"){
  def iso_639_1 = column[String]("iso_639_1", O.Length(2), O.PrimaryKey)
  def name = column[String]("name")

  override def * = (iso_639_1, name).mapTo[Languages]
}
