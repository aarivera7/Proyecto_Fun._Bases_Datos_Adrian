package data_base.tables
import model.classes.Movie
import data_base.TablesQueries.languages
import slick.jdbc.MySQLProfile.api._

class MovieTable (tag: Tag) extends Table[Movie](tag, "movie"){
  def index = column[Int]("index")
  def budget = column[Int]("budget")
  def homepage = column [String]("homepage")
  def idMovie = column[Int]("idMovie",O.PrimaryKey)
  def keywords = column[String]("keywords")
  def originalLanguage = column[String]("original_language", O.Length(2))
  def originalTitle = column[String]("original_title")
  def overview = column[String]("overview")
  def popularity = column[Double]("popularity")
  def releaseDate = column[String]("release_date")
  def revenue = column[Long]("revenue")
  def runTime = column[String]("runtime")
  def status = column[String]("status")
  def tagLine = column[String]("tagline")
  def title = column[String]("title")
  def voteAverage = column[Double]("vote_average")
  def voteCount = column[Int]("vote_count")
  def cast = column[String]("cast")
  def director = column[String]("director")

  override def * = (index, budget, homepage, idMovie, keywords, originalLanguage,
    originalTitle, overview, popularity, releaseDate, revenue, runTime, status, tagLine, title,
    voteAverage, voteCount, cast, director).mapTo[Movie]

  def language =
    foreignKey("fk_movie_language", originalLanguage, languages)(_.iso_639_1,
      onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)/*
  def directorPerson =
    foreignKey("fk_movie_director", director, directors)(_.idDirector)*/
}
