package core2.database;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * executes all SQL statements in a file. Call this program as <br>
 * java -classpath driverPath:. ExecSQL commandFile
 *
 * @author zenghh
 * @version 1.0
 * @email 625111833@qq.com
 * @date 2020/9/14 3:19 PM
 */
public class ExecSQL {
    public static void main(String[] args) throws IOException, SQLException {
//        try(Scanner in = args.length == 0? new Scanner(System.in) :
//                new Scanner(Paths.get(args[0]), StandardCharsets.UTF_8.name())){
        String sql = "";
        Scanner in = new Scanner(Paths.get(ExecSQL.class.getResource("/").getPath(), "sql", "init.sql"));
        try (Connection conn = TestDB.getConnection();
             Statement stat = conn.createStatement()) {
            while (true) {
//                    if (args.length == 0){
//                        System.out.println("Enter command or EXIT to exit:");
//                    }
                if (!in.hasNextLine()) {
                    return;
                }

                String line = in.nextLine().trim();
                if (line.equalsIgnoreCase("EXIT")) {
                    return;
                }
                if (line.endsWith(";")) {
                    sql += line.substring(0, line.length() - 1);
                } else {
                    sql += line;
                    continue;
                }
                try {
                    boolean isResult = stat.execute(sql);
                    sql = "";
                    if (isResult) {
                        try (ResultSet rs = stat.getResultSet()) {
                            TestDB.showResultSet(rs);
                        }
                    } else {
                        int updateCount = stat.getUpdateCount();
                        System.out.println(updateCount + " rows updated");
                    }
                } catch (SQLException ex) {
                    for (Throwable e : ex) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException ex) {
            for (Throwable e : ex) {
                e.printStackTrace();
            }
        }
//        }
    }
}



















