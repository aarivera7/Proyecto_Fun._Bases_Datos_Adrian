/*
Universidad Técnica Particular de Loja
Ingeniería en Ciencias de la Computación
Materia: Fundamentos de Base de Datos - Octubre 2021 - Febrero 2022
Proyecto Final - Ciclo de vida de bases de datos relacionales normalizada
Estudiante: Adrián Alessandro Rivera Cueva | aarivera7@utpl.edu.ec
Link del proyecto en Github: https://github.com/aarivera7/Proyecto_Fun._Bases_Datos_Adrian
Profesor: Nelson Piedra | http://investigacion.utpl.edu.ec/nopiedra
Fecha: Loja, 8 de febrero del 2022
*/

CREATE SCHEMA proy_integrator;
USE proy_integrator;

-- ----------------------------------
DROP TABLE IF EXISTS crew;
DROP TABLE IF EXISTS credit;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS `language`;
DROP TABLE IF EXISTS spoken_language;
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS production_country;
DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS production_company;
DROP TABLE IF EXISTS moviegenres;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS cast;
DROP TABLE IF EXISTS actor;
-- ----------------------------------

--
-- Estructura de la tabla `movie`
--
CREATE TABLE IF NOT EXISTS movie(
	idMovie int(11) NOT NULL,
    index_ int(5) NOT NULL,
    budget int(12) NOT NULL,
    homepage varchar(150) DEFAULT NULL,
    original_language char(2) NOT NULL,
    original_title varchar(75) NOT NULL,
    overview varchar(150) DEFAULT NULL,
    popularity decimal(25,6) NOT NULL,
    release_date date NOT NULL,
    revenue int(10) NOT NULL,
    runtime decimal(4,1) NOT NULL COMMENT 'Tiempo de la película en minutos.',
    status_ varchar(8) NOT NULL,
    tagline varchar(100) DEFAULT NULL,
    title varchar(75) NOT NULL,
    vote_average decimal(2,1) NOT NULL,
    vote_count int(10) NOT NULL,
    cast varchar(125) NOT NULL,
    director varchar(30),
    PRIMARY KEY (idMovie)
);

-- ------------------------------------------------------------------------------------------
-- 1FN Si, despues separar los campos
-- 2FN
-- ---- PK: id_movie, idGenre
-- ----  Dependencias parciales
-- ----- id_movie -> {} (campos adicionales para MOVIES)
-- ----- idGenre -> name 
-- ----- id_movie, idGenre -> (relacion MOVIES_GENRES)
--
-- Tablas resultantes
-- MOVIES_GENRES (PK(id_movie, idGenre))  
-- genre (PK(idGenre), name)

--
-- Estructura de la tabla `genre`
--
CREATE TABLE IF NOT EXISTS genre(
    name varchar(10) NOT NULL,
    PRIMARY KEY (name)
);


--
-- Estructura de la tabla `movies_genres`
--
CREATE TABLE IF NOT EXISTS movies_genres(
	idMovie int(11) NOT NULL,
    name varchar(10) NOT NULL,
    PRIMARY KEY (idMovie, name),
    CONSTRAINT `fk_movies_genres_movie` FOREIGN KEY (idMovie) REFERENCES movie (idMovie),
    CONSTRAINT `fk_movies_genres_genre` FOREIGN KEY (name) REFERENCES genre (name)
);

-- ====================================================================================================================================
-- 1FN Si, despues de convertir el JSON en Tabla Relacional
-- 2FN
-- ---- PK: id_movie, idCompany
-- ---- Dependencias parciales
-- ----- id_movie -> {} (campos adicionales para MOVIES)
-- ----- idCompany -> name 
-- ----- id_movie, idCompany -> (relacion MOVIES_COMPANY) production_companies
--
-- Tablas resultantes
-- production_companies (PK(id_movie, idCompany))  
-- Company (PK(idCompany), name)

--
-- Estructura de la tabla `company`
--
CREATE TABLE IF NOT EXISTS production_company(
    idCompany int(11) NOT NULL,
    name varchar(50) NOT NULL,
    PRIMARY KEY (idCompany)
);

--
-- Creación de la tabla de production_companies
--
DROP TABLE IF EXISTS production_companies;
CREATE TABLE IF NOT EXISTS production_companies (
    idMovie INT(11) NOT NULL,
    idCompany INT(11) NOT NULL,
    PRIMARY KEY (idMovie , idCompany),
    CONSTRAINT `fk_production_companies_movie` FOREIGN KEY (idMovie)
        REFERENCES movie (idMovie),
    CONSTRAINT `fk_production_companies_company` FOREIGN KEY (idCompany)
        REFERENCES company (idCompany)
);

-- ========================================================================================================================0
-- 1FN Si, despues de convertir el JSON en Tabla Relacional
-- 2FN
-- ---- PK: id_movie, iso31661
-- ---- Dependencias parciales
-- ----- id_movie -> {} (campos adicionales para MOVIES)
-- ----- iso31661 -> name 
-- ----- id_movie, iso31661 -> (relacion MOVIES_COUNTRY) production_countries
--
-- Tablas resultantes
-- production_companies (PK(id_movie, iso31661))  
-- country (PK(iso31661), name)

--
-- Estructura de la tabla `country`
--
CREATE TABLE IF NOT EXISTS country(
    iso31661 char(2) NOT NULL,
    name varchar(30) NOT NULL,
    PRIMARY KEY (iso31661)
);


--
-- Creación de la tabla production_countries
--
CREATE TABLE IF NOT EXISTS production_countries (
    idMovie INT(11) NOT NULL,
    iso31661 CHAR(2) NOT NULL,
    PRIMARY KEY (idMovie , iso31661),
    CONSTRAINT `fk_production_countries_movie` FOREIGN KEY (idMovie)
        REFERENCES movie (idMovie),
    CONSTRAINT `fk_production_countries_country` FOREIGN KEY (iso31661)
        REFERENCES country (iso31661)
);


-- =================================================================================================================
-- 1FN Si, despues de convertir el JSON en Tabla Relacional
-- 2FN
-- ---- PK: id_movie, iso6391
-- ----  Dependencias parciales
-- ----- id_movie -> {} (campos adicionales para MOVIES)
-- ----- iso6391 -> name 
-- ----- id_movie, iso31661 -> (relacion MOVIES_LANGUAGES) (spoken_languages)
--
-- Tablas resultantes
-- spoken_languages (PK(id_movie, iso6391))  
-- languages (PK(iso6391), name)

--
-- Estructura de la tabla spoken_languages
--
CREATE TABLE IF NOT EXISTS spoken_languages(
    iso6391 char(2) NOT NULL,
    name varchar(20) NOT NULL,
    PRIMARY KEY (iso6391)
);

--
-- Creación de la tabla spoken languages
--
CREATE TABLE IF NOT EXISTS spoken_languages (
    idMovie INT(11) NOT NULL,
    iso6391 CHAR(2) NOT NULL,
    PRIMARY KEY (idMovie , iso6391),
    CONSTRAINT `fk_spoken_languages_movie` FOREIGN KEY (idMovie)
        REFERENCES movie (idMovie),
    CONSTRAINT `fk_spoken_languages_language` FOREIGN KEY (iso6391)
        REFERENCES `language` (iso6391)
);

-- ======================================================================================================================
-- 1FN Si, despues de convertir el JSON en Tabla Relacional
-- 2FN
-- ---- PK: id_movie, credit_id 
-- ---- Verificar dependencias parciales
-- ----- id_movie -> {} (campos adicionales para MOVIES)
-- ----- credit_id -> job, name, gender, department, id_crew 
-- ----- id_movie, credit_id -> (relacion MOVIES_CREW)
-- 3FN 
-- ----- idPerson -> name, gende (Se cambia el nombre de idCrew por idPerson)(campos para la tabla PERSON)
-- ----- credit_id -> job, department, id_crew (campos para la tabla CREDITS)
-- ----- id_movie, credit_id -> (relacion MOVIES_CREW)(CREW)
--
-- Tablas resultantes
-- CREW (PK(id_movie, credit_id))  
-- CREDITS (PK(credit_id), job, department, idPerson )
-- PERSON (PK(idPerson),name, gender)


--
-- Creación de la tabla credit
--
CREATE TABLE IF NOT EXISTS credit (
    idPerson INT,
    credit_id VARCHAR(25),
    job VARCHAR(75),
    department VARCHAR(25),
    PRIMARY KEY (credit_id),
    CONSTRAINT `fk_credit_person` FOREIGN KEY (idPerson)
	REFERENCES person (idPerson)
);

--
-- Creación de la tabla crew
--
CREATE TABLE IF NOT EXISTS crew (
    idMovie INT(11) NOT NULL,
    creditId VARCHAR(25) NOT NULL,
    PRIMARY KEY (idMovie , creditId),
    CONSTRAINT `fk_crew_movie` FOREIGN KEY (idMovie)
        REFERENCES movie (idMovie),
    CONSTRAINT `fk_crew_credit` FOREIGN KEY (creditId)
        REFERENCES credit (credit_Id)
);

--
-- Estructura de la tabla crew
--
CREATE TABLE IF NOT EXISTS crew(
    idCrew int(11) NOT NULL,
	creditId varchar(40) NOT NULL,
    name varchar(20) NOT NULL,
	gender int(1) NOT NULL COMMENT 'Genero del tripulante, 0 es sin especificar, 1 mujer y 2 hombre' check (genre >= 0 and genre <= 2),
    job varchar(25) NOT NULL,
    department varchar(15) NOT NULL,
    PRIMARY KEY (idCrew, creditId),
    CONSTRAINT `fk_crew_department` FOREIGN KEY (department) REFERENCES department (name)
);

-- =============================================================================
-- 1FN Si, despues separar los campos
-- 2FN
-- ---- PK: id_movie, idActor
-- ----  Dependencias parciales
-- ----- id_movie -> {} (campos adicionales para MOVIES)
-- ----- idActor -> name 
-- ----- id_movie, idActor -> (relacion MOVIES_ACTOR)(CAST)
--
-- Tablas resultantes
-- cast (PK(id_movie, idActor))  
-- actor (PK(idActor), name)

--
-- Creación de la tabla cast
--
DROP TABLE IF EXISTS cast;
CREATE TABLE IF NOT EXISTS cast (
    idMovie INT(11) NOT NULL,
    idActor INT(11) NOT NULL,
    PRIMARY KEY (idMovie , idActor),
    CONSTRAINT `fk_cast_movie` FOREIGN KEY (idMovie)
        REFERENCES movie (idMovie),
    CONSTRAINT `fk_cast_actor` FOREIGN KEY (idActor)
        REFERENCES Actor (idActor)
);

--
-- Creación de la tabla actor
--
CREATE TABLE IF NOT EXISTS actor (
    idActor INT(7) NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (idActor)
);

-- =========================================================================================
-- Consultas a la base de datos
-- Obtener el título de las películas que están dobladas al ucraniano
SELECT `title` FROM `movie` t1
INNER JOIN `spoken_languages` t2 ON iso6391 = 'uk' 
WHERE t1.`idMovie` = t2.`idMovie`;

-- Obtener el título original de las películas con un voto promedio mayor a 8.5
SELECT `original_title` FROM `movie` WHERE `vote_average` > 8.5;

-- Obtener el título de las películas con un presupuesto mayor a 225000000 y que su idioma original sea el ingles
SELECT `title` FROM `movie` WHERE (`budget` >= 225000000) and (`original_language` = 'en');