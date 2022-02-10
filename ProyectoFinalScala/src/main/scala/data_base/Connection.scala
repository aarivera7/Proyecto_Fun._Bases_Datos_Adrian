package data_base
import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException
import slick.jdbc.MySQLProfile.api._
import java.sql.SQLException
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.{Duration, DurationInt}

object Connection {
  def createDataBase(nombreBD : String): Unit = {
    // Creación la Base de Datos en el Gestor
    Await.result(Database.forURL(
      "jdbc:mysql://localhost:3306/",
      "root",
      "root",
      driver = "com.mysql.cj.jdbc.Driver")
      .run(sqlu"CREATE DATABASE #$nombreBD"), 10.seconds)
  }

  def dropDatabase(nombreBD: String): Unit = {
    try {
      // Eliminación la Base de Datos en el Gestor
      Await.result(Database.forURL(
        "jdbc:mysql://localhost:3306/",
        "root",
        "root",
        driver = "com.mysql.cj.jdbc.Driver")
        .run(sqlu"DROP DATABASE #$nombreBD"), 10.seconds)
    } catch {
      // Ignora la falla debido a que la base de datos no existe
      case e: MySQLQueryInterruptedException => if (e
        .getMessage.equals(s"Can't drop database '$nombreBD'; database doesn't exist")) {/* Hacer nada */}
      case e: SQLException => if (e.getMessage
        .equals(s"Can't drop database '$nombreBD'; database doesn't exist")) {/* Hacer nada */}
      case e => throw e // escalar otras excepciones
    }
  }

  def connectionMySQL(nombreBD : String): Database = {
    //Conexión con la base de datos
     Database.forURL(
      s"jdbc:mysql://localhost:3306/$nombreBD",
      "root",
      "root",
      driver = "com.mysql.cj.jdbc.Driver")
  }
  //Se ingresa el nombre del archivo donde se encuentran los parámetros para la conexión a la BB DD
  val db = connectionMySQL("Proj_Integrator")

  //Método para la ejecutar en la base de datos con un tiempo de espera
  def execute[T](action: DBIO[T]): T = Await.result(db.run(action), Duration.Inf)

  //Método para la ejecutar en la base de datos de manera asíncrona
  def future[T] (action: DBIO[T]): Future[T] = db.run(action)
}
