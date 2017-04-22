package org.eclipse.scout.healthcare.server.sql;

public interface AccountSQLs {

  String CREATE_TABLE = ""
      + "CREATE TABLE ACCOUNT "
      + "       ("
      + "       address VARCHAR(255) NOT NULL CONSTRAINT ADDRESS_PK PRIMARY KEY, "
      + "       person_id VARCHAR(64), "
      + "       name VARCHAR(255), "
      + "       password VARCHAR(255)"
      + ") ";

  String SELECT_ALL = ""
      + "SELECT a.person_id, "
      + "       LOWER(a.address), "
      + "       a.name "
      + "FROM   account a "
      + "INTO   :{person}, "
      + "       :{address}, "
      + "       :{accountName} ";

  String CREATE = ""
      + "INSERT INTO account (person_id, address, name, password) "
      + "VALUES (:person, LOWER(:address), :name, :password) ";

  String SELECT = ""
      + "SELECT a.person_id, "
      + "       a.name, "
      + "       a.password "
      + "FROM   account a "
      + "WHERE  LOWER(a.address) = LOWER(:address) "
      + "INTO   :person, "
      + "       :name,"
      + "       :password ";

  String UPDATE = ""
      + "UPDATE account "
      + "SET    person_id = :person, "
      + "       name = :name "
      + "WHERE  LOWER(address) = LOWER(:address) ";

}
