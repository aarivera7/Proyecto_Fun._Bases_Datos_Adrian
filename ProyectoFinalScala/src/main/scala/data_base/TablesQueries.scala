package data_base

import data_base.tables.relationship_tables._
import data_base.tables._
import slick.jdbc.MySQLProfile.api._

object TablesQueries {
  //Creación de los TableQuery para los inserts y consultas
  lazy val movies = TableQuery[MovieTable]
  lazy val languages = TableQuery[LanguagesTable]
  lazy val spokenLanguages = TableQuery[SpokenLanguagesTable]
  lazy val companies = TableQuery[CompanyTable]
  lazy val productionCompanies = TableQuery[ProductionCompaniesTable]
  lazy val countries = TableQuery[CountriesTable]
  lazy val productionCountries = TableQuery[ProductionCountriesTable]
  lazy val genres = TableQuery[GenreTable]
  lazy val movieGenres = TableQuery[MovieGenresTable]
  lazy val credits = TableQuery[CreditTable]
  lazy val crews = TableQuery[CrewTable]
  lazy val persons = TableQuery[PersonTable]
  lazy val directors = TableQuery[DirectorTable]
}
