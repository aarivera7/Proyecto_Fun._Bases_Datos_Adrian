package data_base.tables

import model.classes.Company
import slick.jdbc.MySQLProfile.api._

class CompanyTable(tag: Tag) extends Table[Company](tag, "Company"){
  def idCompany = column[Int]("id", O.PrimaryKey)
  def name = column[String]("name")
  override def * = (idCompany, name).mapTo[Company]
}
