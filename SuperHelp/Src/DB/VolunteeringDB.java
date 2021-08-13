package DB;


import Model.Volunteering;

import java.sql.*;
import java.util.ArrayList;

public class VolunteeringDB {
    public static ArrayList<Volunteering> getAllVolunteeringData(Connection db) throws SQLException {
        String sql = "SELECT * FROM dbso.volunteering";

        ArrayList<Volunteering> volunteerings = new ArrayList<Volunteering>();

        PreparedStatement ps = db.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Volunteering volunteering = new Volunteering(rs.getInt("volunteeringId"),
                    rs.getString("FirstName"), rs.getString("LastName"),
                    rs.getString("Address"), rs.getString("PhoneNumber"),
                    rs.getString("Problem"), rs.getDate("Time"));

            volunteerings.add(volunteering);
        }

        return volunteerings;
    }

    public static void insertData(Connection db, Volunteering volunteering) throws SQLException {
        String sql = "INSERT INTO client (volunteerId, FirstName, LastName, Address, PhoneNumber, Problem, Time) VALUES (?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = db.prepareStatement(sql);
        ps.setInt(1, volunteering.getVolunteerId());
        ps.setString(2, volunteering.getFirstName());
        ps.setString(3, volunteering.getLastName());
        ps.setString(4, volunteering.getAddress());
        ps.setString(5, volunteering.getPhoneNumber());
        ps.setString(6, volunteering.getProblem());
        ps.setDate(7, (Date) volunteering.getTime());
        ps.executeUpdate();
    }

    public static void deleteData(Connection db, Volunteering volunteering) throws SQLException {
        String sql = "DELETE FROM client WHERE volunteerId=?;";

        PreparedStatement ps = db.prepareStatement(sql);
        ps.setInt(1, volunteering.getVolunteerId());
        ps.executeUpdate();
    }
}
