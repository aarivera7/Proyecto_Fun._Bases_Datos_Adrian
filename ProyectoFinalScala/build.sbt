name := "ProyectoFinal"

version := "0.1"

//scalaVersion := "2.13.8"

val kantanCsvVersion = "0.5.0"
//scalaVersion := "2.13.0"

//val kantanCsvVersion = "0.6.3"

libraryDependencies ++= Seq(
  "com.nrinaudo" %% "kantan.csv" % kantanCsvVersion,

  "com.nrinaudo" %% "kantan.csv-generic" % kantanCsvVersion,

  "com.nrinaudo" %% "kantan.csv-enumeratum" % kantanCsvVersion,

  "com.nrinaudo" %% "kantan.csv-jackson" % kantanCsvVersion,

  "com.typesafe.play" %% "play-json" % "2.9.2",

  //SLICK
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "ch.qos.logback"      % "logback-classic" % "1.2.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",
  "mysql" % "mysql-connector-java" % "8.0.23"

)

/*
  // Provides instances for refined types.
  "com.nrinaudo" %% "kantan.csv-refined" % kantanCsvVersion,

  // Provides instances for libra types.SNAPSHOT
  "com.nrinaudo" %% "kantan.csv-libra" % kantanCsvVersion,*/
/*
// Java 8 date and time instances.
"com.nrinaudo" %% "kantan.csv-java8" % kantanCsvVersion,

// Provides scalaz type class instances for kantan.csv, and vice versa.
"com.nrinaudo" %% "kantan.csv-scalaz" % kantanCsvVersion,

// Provides cats type class instances for kantan.csv, and vice versa.
"com.nrinaudo" %% "kantan.csv-cats" % kantanCsvVersion,
 // Provides instances for refined types.
"com.nrinaudo" %% "kantan.csv-refined" % kantanCsvVersion,

// Provides instances for libra types.SNAPSHOT
"com.nrinaudo" %% "kantan.csv-libra" % kantanCsvVersion*/