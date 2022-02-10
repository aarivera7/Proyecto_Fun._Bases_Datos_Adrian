package model.classes

case class Movie(index: Int, budget: Int, homepage: String, id: Int, keywords: String,
                 originalLanguage: String, originalTitle: String, overview: String,
                 popularity: Double, releaseDate: String, revenue: Long, runTime: String,
                 status: String, tagLine: String, title: String, voteAverage: Double,
                 voteCount: Int, cast: String, director: String) extends Serializable
