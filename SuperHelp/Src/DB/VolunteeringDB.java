package DB;


import Model.Volunteering;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
