package data_base

import model.classes.{Company, Country, Credit, Languages, Movie, Person}
import model.classes.relationship_classes.{Crew, Director, ProductionCompanies, ProductionCountries, SpokenLanguages}
import Connection.execute
import TablesQueries._
import csv.CSV.extractMovieCSV
import play.api.libs.json.Json
import slick.jdbc.MySQLProfile.api._

object Inserts {
  //Método para ingresar los datos de la tabla movie a la base de datos
  def insertToDataBaseMovie(): Unit = {
    val data = extractMovieCSV().map(x => Movie(x.index,x.budget,x.homepage,x.id,x.keywords,
      x.original_language, x.original_title, x.overview, x.popularity, x.release_date, x.revenue,x.runtime,
      x.status, x.tagline,x.title, x.voteAverage, x.voteCount,x.cast, x.director))

    execute(movies ++= data)
  }

  //Método para ingresar los datos de la tabla languages a la base de datos
  def insertToDataBaseLanguage(): Unit = {
    val data = extractMovieCSV().map(x => Json.parse(x.spoken_languages))
      .flatMap(fm => fm \\ "name")
      .map(_.as[String])
    val data2 = extractMovieCSV().map(x => Json.parse(x.spoken_languages))
      .flatMap(fm => fm \\ "iso_639_1")
      .map(_.as[String])
    var i: Int = 0
    var data3: List[Languages] = List.empty
    while (i < data.length){
      data3 = data3.::(Languages(data2(i), data(i)))
      i = i + 1
    }
    data3 = data3.distinct
    execute(languages ++= data3)

    /*val data = extractMovieCSV.map(x => Json.parse(x.spoken_languages)).distinct
      .map(x => Languages(x("iso_639_1").toString(), x("name").toString())).distinct
    println(data)*/
  }

  //Método para ingresar los datos de la tabla spoken_languages a la base de datos
  def insertToDataBaseSpokenLanguages(): Unit ={
    val data = extractMovieCSV().map(x => (x.id, Json.parse(x.spoken_languages)))
      .map(x => (x._1, x._2 \\ "iso_639_1"))

    var data1: List[SpokenLanguages] = List.empty
    data.foreach(x =>
      x._2.foreach(y =>
        data1 = data1.:: (SpokenLanguages(x._1, y.as[String]))
      ))
    data1 = data1.distinct
    execute(spokenLanguages ++= data1)
  }

  def insertToDataBaseCompany(): Unit = {
    val data = extractMovieCSV().map(x => Json.parse(x.production_companies))
      .flatMap(fm => fm \\ "name")
      .map(_.as[String])
    val data2 = extractMovieCSV().map(x => Json.parse(x.production_companies))
      .flatMap(fm => fm \\ "id")
      .map(_.as[Int])
    var i: Int = 0
    var data3: List[Company] = List.empty
    while (i < data.length){
      data3 = data3.::(Company(data2(i), data(i)))
      i = i + 1
    }
    data3 = data3.distinct
    execute(companies ++= data3)
  }

  //Método para ingresar los datos de la tabla spoken_languages a la base de datos
  def insertToDataBaseProductionCompanies(): Unit ={
    val data = extractMovieCSV().map(x => (x.id, Json.parse(x.production_companies)))
      .map(x => (x._1, x._2 \\ "id"))

    var data1: List[ProductionCompanies] = List.empty
    data.foreach(x =>
      x._2.foreach(y =>
        data1 = data1.:: (ProductionCompanies(x._1, y.as[Int]))
      ))
    data1 = data1.distinct
    execute(productionCompanies ++= data1)
  }

  def insertToDataBaseCountry(): Unit = {
    val data = extractMovieCSV().map(x => Json.parse(x.production_countries))
      .flatMap(fm => fm \\ "name")
      .map(_.as[String])
    val data2 = extractMovieCSV().map(x => Json.parse(x.production_countries))
      .flatMap(fm => fm \\ "iso_3166_1")
      .map(_.as[String])
    var i: Int = 0
    var data3: List[Country] = List.empty
    while (i < data.length){
      data3 = data3.::(Country(data2(i), data(i)))
      i = i + 1
    }
    data3 = data3.distinct
    execute(countries ++= data3)
  }

  //Método para ingresar los datos de la tabla spoken_languages a la base de datos
  def insertToDataBaseProductionCountries(): Unit ={
    val data = extractMovieCSV().map(x => (x.id, Json.parse(x.production_countries)))
      .map(x => (x._1, x._2 \\ "iso_3166_1"))

    var data1: List[ProductionCountries] = List.empty
    data.foreach(x =>
      x._2.foreach(y =>
        data1 = data1.:: (ProductionCountries(x._1, y.as[String]))
      ))
    data1 = data1.distinct
    execute(productionCountries ++= data1)
  }

  def insertToDataBasePerson(): Unit = {
    val data = extractMovieCSV().map(x => Json.parse(x.crew))
      .flatMap(fm => fm \\ "name")
      .map(_.as[String])
    val data2 = extractMovieCSV().map(x => Json.parse(x.crew))
      .flatMap(fm => fm \\ "gender")
      .map(_.as[Int])
    val data3 = extractMovieCSV().map(x => Json.parse(x.crew))
      .flatMap(fm => fm \\ "id")
      .map(_.as[Int])
    var i: Int = 0
    var dataFinal: List[Person] = List.empty
    while (i < data.length){
      if(data(i).charAt(0) == '\'' && data(i).charAt(data(i).length-1) == '\''){
        dataFinal = dataFinal.::(Person(data(i).substring(1,data(i).length-1), data2(i), data3(i)))
        i = i + 1
      }else {
        dataFinal = dataFinal.::(Person(data(i), data2(i), data3(i)))
        i = i + 1
      }
    }
    dataFinal = dataFinal.distinct
    execute(persons ++= dataFinal)
  }

  def insertToDataBaseCredit(): Unit = {
    val data = extractMovieCSV().map(x => Json.parse(x.crew))
      .flatMap(fm => fm \\ "credit_id")
      .map(_.as[String])
    val data2 = extractMovieCSV().map(x => Json.parse(x.crew))
      .flatMap(fm => fm \\ "id")
      .map(_.as[Int])
    val data3 = extractMovieCSV().map(x => Json.parse(x.crew))
      .flatMap(fm => fm \\ "department")
      .map(_.as[String])
    val data4 = extractMovieCSV().map(x => Json.parse(x.crew))
      .flatMap(fm => fm \\ "job")
      .map(_.as[String])
    var i: Int = 0
    var dataFinal: List[Credit] = List.empty
    while (i < data.length){
      dataFinal = dataFinal.::(Credit(data(i), data2(i), data3(i), data4(i)))
      i = i + 1
    }
    dataFinal = dataFinal.distinct
    execute(credits ++= dataFinal)
  }

  //Método para ingresar los datos de la tabla spoken_languages a la base de datos
  def insertToDataBaseCrew(): Unit ={
    val data = extractMovieCSV().map(x => (x.id, Json.parse(x.crew)))
      .map(x => (x._1, x._2 \\ "credit_id"))

    var data1: List[Crew] = List.empty
    data.foreach(x =>
      x._2.foreach(y =>
        data1 = data1.:: (Crew(x._1, y.as[String]))
      ))
    data1 = data1.distinct
    execute(crews ++= data1)
  }

  def insertToDataBaseDirector(): Unit = {
    val data1 = extractMovieCSV().map(x => Json.parse(x.crew))
    .flatMap(fm => fm \\ "id")
    .map(_.as[Int])
    val data2 = extractMovieCSV().map(x => Json.parse(x.crew))
    .flatMap(fm => fm \\ "job")
    .map(_.as[String])
    var i: Int = 0
    var dataFinal : List[Director] = List.empty
    while (i < data1.length){
      if(data2(i) == "Director"){
        dataFinal = dataFinal.::(Director(data1(i)))
        }
      i = i + 1
      }
    dataFinal = dataFinal.distinct
    execute(directors ++= dataFinal)
    }

  def main(args: Array[String]): Unit = {
    insertToDataBaseDirector()
  }
}
