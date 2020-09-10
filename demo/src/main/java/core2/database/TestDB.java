package core2.database;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/9/9 4:40 PM
 * @version 1.0
 */
public class TestDB {
    public static void main(String[] args) throws IOException {
        try {
            runTest();
        } catch (SQLException throwables) {
            for (Throwable e : throwables){
                e.printStackTrace();
            }
        }
    }

    public static void runTest() throws IOException, SQLException {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()){
            statement.executeUpdate("  CREATE TABLE IF NOT EXISTS greetings (Message char(20))  ");
            statement.executeUpdate("insert into greetings values ('Hello,World!')");

            try (ResultSet result = statement.executeQuery("select * from greetings")){
                if (result.next()){
                    System.out.println(result.getString(1));
                }
            }
            statement.executeUpdate("drop table greetings");
        }

    }

    public static Connection getConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        String propUrl = TestDB.class.getClass().getResource("/").getPath();
        try(InputStream in = Files.newInputStream(Paths.get(propUrl + "database.properties"))){
            properties.load(in);
        }
        String drivers = properties.getProperty("jdbc.drivers");
        if (drivers != null){
            System.setProperty("jdbc.drivers",drivers);
        }
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        return DriverManager.getConnection(url,username,password);
    }
}




















