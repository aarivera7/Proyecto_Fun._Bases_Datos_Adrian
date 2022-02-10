import data_base.Connection.{createDataBase, dropDatabase, execute}
import data_base.Inserts._
import data_base.TablesQueries._
import model.classes.Languages
import slick.jdbc.MySQLProfile.api._

object Main {
  def main(args: Array[String]): Unit = {
    dropDatabase("Proj_Integrator")
    createDataBase("Proj_Integrator")

    // Creación de las tablas en la base de datos
    execute(languages.schema.create andThen
      movies.schema.create andThen
      spokenLanguages.schema.create andThen
      companies.schema.create andThen
      productionCompanies.schema.create andThen
      countries.schema.create andThen
      productionCountries.schema.create andThen
      genres.schema.create andThen
      movieGenres.schema.create andThen
      persons.schema.create andThen
      credits.schema.create andThen
      crews.schema.create andThen
      directors.schema.create)

    //Ingresando los datos de la tabla languages a la base de datos
    insertToDataBaseLanguage()

    //Se ingresa el dato faltante
    execute(languages += Languages("nb", "noruego bokmål"))

    //Ingresando los datos de la tabla movie a la base de datos
    insertToDataBaseMovie()

    //Ingresando los datos de la tabla spoken languages a la base de datos
    insertToDataBaseSpokenLanguages()

    //Ingresando los datos de la tabla company a la base de datos
    insertToDataBaseCompany()

    //Ingresando los datos de la tabla Production Companies a la base de datos
    insertToDataBaseProductionCompanies()

    //Ingresando los datos de la tabla Country a la base de datos
    insertToDataBaseCountry()

    //Ingresando los datos de la tabla Production Countries a la base de datos
    insertToDataBaseProductionCountries()

    //Ingresando los datos de la tabla Person a la base de datos
    insertToDataBasePerson()

    //Ingresando los datos de la tabla Credit a la base de datos
    insertToDataBaseCredit()

    //Ingresando los datos de la tabla Crew a la base de datos
    insertToDataBaseCrew()
  }
}
