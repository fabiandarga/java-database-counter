package de.fabiandarga.databasecounter;
import java.sql.*;

public class CounterDAO {
    public int getCounterValue() {
        String sql = "SELECT value FROM counter WHERE id = 1";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void incrementCounter() {
        String updateSql = "UPDATE counter SET value = value + 1 WHERE id = 1";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(updateSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}