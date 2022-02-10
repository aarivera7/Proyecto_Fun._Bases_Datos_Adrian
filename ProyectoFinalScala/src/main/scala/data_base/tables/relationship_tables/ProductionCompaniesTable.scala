package data_base.tables.relationship_tables

import model.classes.relationship_classes.ProductionCompanies
import data_base.TablesQueries.{companies, movies}
import slick.jdbc.MySQLProfile.api._

class ProductionCompaniesTable(tag: Tag) extends Table[ProductionCompanies](tag, "production_companies"){
  def idMovie = column[Int]("idMovie")
  def idCompany = column[Int]("idCompany")

  override def * = (idMovie, idCompany).mapTo[ProductionCompanies]

  def pk = primaryKey(null,(idMovie, idCompany))
  def movie =
    foreignKey("fk_production_companies_movie", idMovie, movies)(_.idMovie)
  def company =
    foreignKey("fk_production_companies_company", idCompany, companies)(_.idCompany)
}
