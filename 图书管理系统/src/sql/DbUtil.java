package sql;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbUtil {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/图书管理系统?characterEncoding=utf-8&serverTimezone=UTC";
    private static Connection con = null;
    private static Statement smt = null;
    private static ResultSet rs = null;

    //连接数据库
    private static Connection createConnection() {
        try {

            Class.forName(driver);
            return DriverManager.getConnection(URL, "root", "admin");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("Can't load Driver");
        }
        return null;
    }
    //更新数据库
    public static int runUpdate(String sql) throws SQLException {
        int count = 0;
        if (con == null) {
            con = createConnection();
        }
        if (smt == null) {
            smt = con.createStatement();
        }

        count = smt.executeUpdate(sql);

        if (smt != null) {
            smt.close();
            smt = null;
        }
        if (con != null) {
            con.close();
            con = null;
        }
        return count;
    }
    //查询
    public static ResultSet runQuery(String sql) throws SQLException {
        if (con == null) {
            con = createConnection();
        }
        if (smt == null) {
            smt = con.createStatement();
        }
        return smt.executeQuery(sql);
    }
    public static void realeaseAll() {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (smt != null) {
            try {
                smt.close();
                smt = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (SQLException ex) {
                Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    //关闭数据库
    public static void closeConnection(Connection conn) {
        System.out.println("...");
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
