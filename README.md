## Universidad Técnica Particular de Loja
![image](https://user-images.githubusercontent.com/56032735/120117919-4f322780-c155-11eb-86b1-415562139384.png)
# Proyecto Integrador 2022
Presentación del proyecto final de Fundamentos de Bases de Datos.

## Datos del Proyecto
#### Ingeniería en Ciencias de la Computación
#### Proyecto Final - Ciclo de vida de bases de datos relacionales normalizada

##### Materia:
Fundamentos de Base de Datos 

##### Periodo: 
Octubre 2021 - Febrero 2022

##### Estudiante:
Adrián Alessandro Rivera Cueva | aarivera7@utpl.edu.ec | aarivera7

##### Link del proyecto en Github: 
https://github.com/aarivera7/Proyecto_Fun._Bases_Datos_Adrian

##### Profesor: 
Nelson Piedra | http://investigacion.utpl.edu.ec/nopiedra

##### Fecha: 
Loja, 8 de febrero del 2022

### Descrición del proyecto
Este proyecto fue elaborado por Adrián Rivera (aarivera7) estudiante de tercer ciclo de la UTPL,
con todos los conocimíentos adquiridos en la materia de Fundamentos de Bases de Datos, el objetivo
principalde este trabajo es extraer todo los datos de un archivo CSV, que se nos entrego con una
tabla universal de datos sobre películas, aplicar el proceso de normalización, para luego subir la 
información referente a cada tabla con los datos del CSV.

## Descripción de los archivos cargados al repositorio:

|Nombre del archivo|Descripción|
|------------------------------------------------------------------------|----------------------------------------|
|Informe de Proyecto Integrador Fundamentos de Bases de Datos S14 B2.docx|En este documento se encuentra el informe con todo el proceso de normalización aplicado de la tabla universar propuesta para sacar una Base de Datos persistente.|
|Proyecto integrador Fundamentos de Bases de Datos S16 B2.pptx |En este archivo se encuentra las diapositivas utilizadas en la presentación del proyecto ante el profesor de la materia.|
|Proceso_carga_de_datos.sql|En este archivo está el código completo para realizar la creación de las tablas luego de haberaplicado el proceso de normaliación, también está la inserción, limpieza y correción de datos. En el script practicamente está listo para ejecutar y ensamblar la base de datos, partiendo enque ya se cargo la tabla universal que contiene los datos del CSV|
|DDL_proyecto.sql|En este archivo va a encontrar todos los CREATEs de las tablas y también está documentado el procesode normalización que se utilizo para llegar a ellas.|
|Modelo_Proyecto_Integrador.mwb|En este fichero está almacenado el modelo conceptual de la Base de Datos al que llegamos siguiendo los conceptos de normalización.|
|Normalización Proyecto Final C3.xlsx|En este Excel se encuentran todas las tablas que se obtuvo con la normalización.|
|INSERTS MOVIEDATASET.sql|En esté scrip se encuentran todos los inserts correspondiente al CSV|
|ProyectoFinalScala|Se incluye un proyecto en Scala que crea las tablas e inserta los datos a la Base de Datos.|

##### Nota para el archivo Proceso_carga_de_datos.sql: 
Si lee detenidamente el archivo Proceso_carga_de_datos.sql vera que existen unas clausulas UPDATE comentadas, estas
son importantes que descomenten la primera vez que ejecuten el archivo, ya que estos corrigen los
datos dañados en la tabla universal.

##### Nota para el archivo Modelo_Proyecto_Integrador.mwb:
Este archivo fue creado en MySQL Workbench, por lo que le recomendamos solo habra el archivo
en ese programa.
