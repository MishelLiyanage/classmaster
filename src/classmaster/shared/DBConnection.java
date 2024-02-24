package classmaster.shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bhagy
 */
public class DBConnection {

    private String host;
    private String username;
    private String password;
    private Connection connection;

    public DBConnection(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(host, username, password);
    }

    public int executeUpdate(String query, Object[] params) throws SQLException {
        PreparedStatement st = connection.prepareStatement(query);
        prepareStatement(st, params);
        return st.executeUpdate();
    }

    public ResultSet execute(String query, Object[] params) throws SQLException {
        PreparedStatement st = connection.prepareStatement(query);
        prepareStatement(st, params);
        return st.executeQuery();
    }

    private void prepareStatement(PreparedStatement st, Object[] params) throws SQLException {

        for (int i = 0; i < params.length; i++) {
            Object obj = params[i];
            if (obj instanceof Integer) {
                st.setInt(i + 1, (int) obj);
            } else if (obj instanceof Double) {
                st.setDouble(i + 1, (Double) obj);
            } else if (obj instanceof String) {
                st.setString(i + 1, (String) obj);
            } else if (obj instanceof LocalDate) {
                st.setDate(i + 1, java.sql.Date.valueOf((LocalDate) obj));

            } else if (obj instanceof LocalTime) {
                st.setTime(i + 1, java.sql.Time.valueOf((LocalTime) obj));
            }

        }

    }

}
