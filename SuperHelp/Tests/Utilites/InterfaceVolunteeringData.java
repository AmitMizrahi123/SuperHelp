package Utilites;

import Model.Volunteering;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface InterfaceVolunteeringData {
    String _selectAllVolunteeringInDb = "select * from dbso.volunteering";

    Volunteering _stamVolunteering = new Volunteering(Integer.MAX_VALUE, "Admin Admin",
            22, "Male", "0000000001", "Admin", "Stam");

    int _numberOfVolunteering = 1000;
}
