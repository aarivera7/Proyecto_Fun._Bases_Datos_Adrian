import data_base.Connection.execute
import data_base.TablesQueries.{movies, spokenLanguages}
import slick.jdbc.MySQLProfile.api._

object Queries {
  def main(args: Array[String]): Unit = {
    val query1 = execute(movies.filter(_.budget >= 225000000).filter(_.originalLanguage === "en").map(_.title).result)
    println("=========================================== Consulta 1 ===========================================")
    println("Obtener el título de las películas con un presupuesto mayor a 225000000 y que su idioma original sea el ingles")
    query1.foreach(println)

    val query2 = execute(movies.filter(_.voteAverage > 8.5).map(_.originalTitle).result)
    println("=========================================== Consulta 2 ===========================================")
    println("Obtener el título original de las películas con un voto promedio mayor a 8.5")
    query2.foreach(println)

    val query3 = execute((for {
        (m, _) <- movies join spokenLanguages.filter(_.iso_639_1 === "uk") on (_.idMovie === _.idMovie)
      } yield m.title).result
    )
    println("=========================================== Consulta 3 ===========================================")
    println("Obtener el título de las películas que están dobladas al ucraniano")
    query3.foreach(println)
  }
}
