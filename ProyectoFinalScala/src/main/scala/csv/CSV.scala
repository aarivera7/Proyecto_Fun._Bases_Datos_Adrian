package csv
import kantan.csv.ops.toCsvInputOps
import kantan.csv.{RowDecoder, rfc}
import java.net.URL

object CSV {
  //Case class que representa los datos almacenados en el CSV
  case class MovieCSV(
                       index: Int,
                       budget: Int,
                       genres: String,
                       homepage: String,
                       id: Int,
                       keywords: String,
                       original_language: String,
                       original_title: String,
                       overview: String,
                       popularity: Double,
                       production_companies: String,
                       production_countries: String,
                       release_date: String,
                       revenue: Long,
                       runtime: String,
                       spoken_languages: String,
                       status: String,
                       tagline: String,
                       title: String,
                       voteAverage: Double,
                       voteCount: Int,
                       cast: String,
                       crew: String,
                       director: String)
  // Se divide el CSV en dos para que la librería Kantan lo pueda leer,
  // ya que una tupla puede albergar solo 22 atributos.
  // Primera parte del CSV
  case class MovieCSV1(
                        index: Int,
                        budget: Int,
                        genres: String,
                        homepage: String,
                        id: Int,
                        keywords: String,
                        original_language: String,
                        original_title: String,
                        overview: String,
                        popularity: Double,
                        production_companies: String,
                        production_countries: String,
                        release_date: String
                      )
// Segunda parte del CSV
  case class MovieCSV2(
                        revenue: Long,
                        runtime: String,
                        spoken_languages: String,
                        status: String,
                        tagline: String,
                        title: String,
                        voteAverage: Double,
                        voteCount: Int,
                        cast: String,
                        crew: String,
                        director: String
                      )

  //val path2DataFile = "C:\\Users\\adria\\Documents\\movie_dataset_corregido.csv"
  val locationCsv: URL = getClass.getResource("/movie_dataset_corregido.csv")

  //Método que extrae los datos del CSV
  def extractMovieCSV(): Seq[MovieCSV] = {
    implicit val movieDecoder1: RowDecoder[MovieCSV1] = RowDecoder.decoder(0,1,2,3,4,5,6,
      7,8,9,10,11,12)(MovieCSV1)

    implicit val movieDecoder2: RowDecoder[MovieCSV2] = RowDecoder.decoder(13,14,15,16,17,18,
      19,20,21,22,23)(MovieCSV2)

    implicit val movieDecoder: RowDecoder[MovieCSV] = RowDecoder.from{ row =>
      for {
        p1 <- movieDecoder1.decode(row)
        p2 <- movieDecoder2.decode(row)
      } yield MovieCSV(p1.index, p1.budget, p1.genres, p1.homepage, p1.id, p1.keywords, p1.original_language,
        p1.original_title, p1.overview, p1.popularity, p1.production_companies, p1.production_countries,
        p1.release_date, p2.revenue, p2.runtime, p2.spoken_languages, p2.status, p2.tagline, p2.title,p2.voteAverage,
        p2.voteCount, p2.cast, p2.crew, p2.director)
    }

    val dataSource = locationCsv.readCsv[List, MovieCSV](rfc.withHeader(true))
    val rows = dataSource.filter(row => row.isRight)
    rows.collect({ case Right(movie) => movie })
  }

  def main(args: Array[String]): Unit = {
    println(extractMovieCSV())
  }
}
