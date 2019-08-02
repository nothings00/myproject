package util;

import java.sql.*;

/**
 * 数据库连接工具类
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-29 15:49
 * @version 1.0.0
 */
public class DBUtil {
    public static final String URL="";
    public static final String USER="";
    public static final String PASSWORD="";
    public static final String MYSQL_DRIVER="";

    //桥接模式
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动
        Class.forName(MYSQL_DRIVER);
        //2.获得数据库连接
        Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        //3.操作数据库，实现增删改查
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from user");
        //如果有返回数据，rs.next()返回true
        while (rs.next()){
            System.out.println("姓名："+rs.getString("name")
                    +"年龄："+rs.getInt("age"));
        }
    }
}
