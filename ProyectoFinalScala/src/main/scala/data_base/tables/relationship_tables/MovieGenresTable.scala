package data_base.tables.relationship_tables

import model.classes.relationship_classes.MovieGenres
import data_base.TablesQueries.{genres, movies}
import slick.jdbc.MySQLProfile.api._

class MovieGenresTable (tag: Tag) extends Table[MovieGenres](tag, "movie_genres"){
  def idMovie = column[Int]("idMovie")
  def idGenre = column[Int]("idGenre")
  override def * = (idMovie, idGenre).mapTo[MovieGenres]

  def movie =
    foreignKey("fk_movie_genres_movie", idMovie, movies)(_.idMovie)
  def genre =
    foreignKey("fk_movie_genres_genre", idGenre, genres)(_.idGenre)
}
