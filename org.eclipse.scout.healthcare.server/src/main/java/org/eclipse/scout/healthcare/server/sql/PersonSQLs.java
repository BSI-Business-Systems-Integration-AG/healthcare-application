package org.eclipse.scout.healthcare.server.sql;

public interface PersonSQLs {

  String CREATE_TABLE = ""
      + "CREATE   TABLE PERSON "
      + "         (person_id VARCHAR(64) NOT NULL CONSTRAINT PERSON_PK PRIMARY KEY, "
      + "          first_name VARCHAR(64), "
      + "          last_name VARCHAR(64), "
      + "          picture_url VARCHAR(512), "
      + "          date_of_birth DATE, "
      + "          gender VARCHAR(1), "
      + "          street VARCHAR(64), "
      + "          city VARCHAR(64), "
      + "          country VARCHAR(2), "
      + "          phone VARCHAR(20), "
      + "          mobile VARCHAR(20), "
      + "          email VARCHAR(64), "
      + "          occupation_id VARCHAR(64), "
      + "          notes VARCHAR(1024) "
      + "         )";

  String DROP_TABLE = "DROP TABLE PERSON";

  String LOOKUP = ""
      + "SELECT   person_id, "
      + "         first_name || ' ' || last_name "
      + "FROM     PERSON "
      + "WHERE    1 = 1 "
      + "<key>    AND person_id = :key</key> "
      + "<text>   AND (UPPER(first_name) LIKE UPPER('%'||:text||'%') "
      + "         OR UPPER(last_name) LIKE UPPER('%'||:text||'%')) "
      + "</text>"
      + "<all> </all>";

  String PAGE_SELECT = ""
      + "SELECT   person_id, "
      + "         first_name, "
      + "         last_name, "
      + "         city, "
      + "         country, "
      + "         phone, "
      + "         mobile, "
      + "         email, "
      + "         occupation_id "
      + "FROM     PERSON ";

  String PAGE_DATA_SELECT_INTO = ""
      + "INTO     :{page.personId}, "
      + "         :{page.firstName}, "
      + "         :{page.lastName}, "
      + "         :{page.city}, "
      + "         :{page.country}, "
      + "         :{page.phone}, "
      + "         :{page.mobile}, "
      + "         :{page.email}, "
      + "         :{page.occupation}";

  String INSERT = ""
      + "INSERT   INTO "
      + "PERSON  (person_id) "
      + "VALUES   (:personId)";

  String SELECT = ""
      + "SELECT   first_name, "
      + "         last_name, "
      + "         picture_url, "
      + "         date_of_birth, "
      + "         gender, "
      + "         phone, "
      + "         mobile, "
      + "         email, "
      + "         street, "
      + "         city, "
      + "         country, "
      + "         notes, "
      + "         occupation_id "
      + "FROM     PERSON "
      + "WHERE    person_id = :personId "
      + "INTO     :firstName, "
      + "         :lastName, "
      + "         :pictureUrl, "
      + "         :dateOfBirth, "
      + "         :genderGroup, "
      + "         :phone, "
      + "         :mobile, "
      + "         :email, "
      + "         :street, "
      + "         :city, "
      + "         :country, "
      + "         :notes, "
      + "         :occupation ";

  String UPDATE = ""
      + "UPDATE   PERSON "
      + "SET      first_name = :firstName, "
      + "         last_name = :lastName, "
      + "         picture_url = :pictureUrl, "
      + "         date_of_birth = :dateOfBirth, "
      + "         gender = :genderGroup, "
      + "         phone  = :phone, "
      + "         mobile = :mobile, "
      + "         email = :email, "
      + "         street = :street, "
      + "         city = :city, "
      + "         country = :country, "
      + "         notes = :notes, "
      + "         occupation_id = :occupation "
      + "WHERE    person_id = :personId";

  String INSERT_SAMPLE = ""
      + "INSERT   INTO PERSON "
      + "         (person_id, "
      + "          first_name, "
      + "          last_name, "
      + "          picture_url, "
      + "          date_of_birth, "
      + "          gender, "
      + "          street, "
      + "          city, "
      + "          country, "
      + "          occupation_id) "
      + " ";

  String VALUES_DOCTOR_01 = ""
      + "VALUES   ('prs01', "
      + "          'Andrea', "
      + "          'Adams', "
      + "          'http://media.richulradkon.de/public/bsi/Andrea_Adams_Doctor.png', "
      + "          '26.11.1985', "
      + "          'F', "
      + "          null, "
      + "          null, "
      + "          null, "
      + "          'OCCUPATION.DOCTOR')";

  String VALUES_DOCTOR_02 = ""
      + "VALUES   ('prs02', "
      + "          'Marta', "
      + "          'Marks', "
      + "          'http://media.richulradkon.de/public/bsi/Marta_Marks_Doctor.png', "
      + "          '02.05.1979', "
      + "          'F', "
      + "          null, "
      + "          null, "
      + "          null, "
      + "          'OCCUPATION.DOCTOR')";

  String VALUES_DOCTOR_03 = ""
      + "VALUES   ('prs03', "
      + "          'Sven', "
      + "          'Federkiel', "
      + "          'http://media.richulradkon.de/public/bsi/Sven_Federkiel_Doctor.png', "
      + "          '23.02.1975', "
      + "          'M', "
      + "          null, "
      + "          null, "
      + "          null, "
      + "          'OCCUPATION.DOCTOR')";

  String VALUES_NURSE_01 = ""
      + "VALUES   ('prs04', "
      + "          'Sarah', "
      + "          'Smith', "
      + "          'http://media.richulradkon.de/public/bsi/Sarah_Smith.png', "
      + "          '14.10.1981', "
      + "          'F', "
      + "          null, "
      + "          null, "
      + "          null, "
      + "          'OCCUPATION.NURSE')";

  String VALUES_NURSE_02 = ""
      + "VALUES   ('prs05', "
      + "          'Sergio', "
      + "          'Sapienti', "
      + "          'http://media.richulradkon.de/public/bsi/Sergio_Sapienti.png', "
      + "          '11.09.1978', "
      + "          'M', "
      + "          null, "
      + "          null, "
      + "          null, "
      + "          'OCCUPATION.NURSE')";

  String VALUES_NURSE_03 = ""
      + "VALUES   ('prs06', "
      + "          'Vicky', "
      + "          'Valetti', "
      + "          'http://media.richulradkon.de/public/bsi/Vicky_Valetti.png', "
      + "          '21.03.1988', "
      + "          'F', "
      + "          null, "
      + "          null, "
      + "          null, "
      + "          'OCCUPATION.NURSE')";

  String SELECT_RANDOM = ""
      + "SELECT person_id FROM PERSON ORDER BY RANDOM() INTO :employee";

  String SELECT_DISPLAY_TEXT = ""
      + "SELECT   first_name || ' ' || last_name "
      + "FROM     PERSON "
      + "WHERE    person_id = :employee "
      + "INTO     :employeeDisplayText ";

}
