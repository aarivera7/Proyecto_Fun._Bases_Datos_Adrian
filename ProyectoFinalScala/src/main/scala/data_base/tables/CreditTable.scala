package data_base.tables

import model.classes.Credit
import data_base.TablesQueries.persons
import slick.jdbc.MySQLProfile.api._

class CreditTable (tag: Tag)extends Table[Credit](tag, "Credit") {
  def creditId = column[String]("credit_id", O.PrimaryKey, O.Length(25))
  def idPerson = column[Int]("id_Person")
  def department = column[String]("department")
  def job = column[String]("job")

  override def * = (creditId, idPerson, department, job).mapTo[Credit]

  def person =
    foreignKey("fk_credit_person", idPerson, persons)(_.id)
}
