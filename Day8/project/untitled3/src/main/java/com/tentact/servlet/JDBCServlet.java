package com.tentact.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/jdbc")
public class JDBCServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false";
            String username = "root";
            String password = "2002";
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "SELECT id, name, age FROM t_person WHERE id=2";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                writer.println("<p style='font-size:40px;color:red'>" + id + "</p>");
                writer.println("<p>" + name + "</p>");
                writer.println("<p>" + age + "</p>");
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
