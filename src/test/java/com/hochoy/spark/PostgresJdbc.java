package com.hochoy.spark;

import java.sql.*;

/**
 * Describe:
 *
 * @author hochoy <hochoy18@sina.com>
 * @version V1.0.0
 * @date 2019/2/28
 */
public class PostgresJdbc {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            String url = "jdbc:postgresql://192.168.1.209:5433/perf_action_db";
            Connection con = DriverManager.getConnection(url, "postgres", "123456");
            Statement st = con.createStatement();
            String sql = "select count(*) from perf_action_tab_1 ";
            long start = System.currentTimeMillis();
            ResultSet rs = st.executeQuery(sql);
            long end = System.currentTimeMillis();
            System.out.println("end - start:  "+ Long.toString(end - start));
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int columnCount = rsmd.getColumnCount();
//            while (rs.next()) {
//                for (int i = 1; i <= columnCount; i++) {
//                    System.out.print(rs.getString(i)+"\t");
//                }
//                System.out.println();
//            }
            rs.close();
            st.close();
            con.close();

        } catch (Exception ee) {
            System.out.print(ee.getMessage());
        }
    }
}
