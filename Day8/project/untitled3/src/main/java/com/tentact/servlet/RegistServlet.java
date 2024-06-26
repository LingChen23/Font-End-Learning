package com.tentact.servlet;

import com.tentact.util.JDBCTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/regist")
public class RegistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前台发送过来的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        PrintWriter out = resp.getWriter();
        out.println(username);
        out.println(password);

        Connection conn = null;
        PreparedStatement ps = null;

        conn = JDBCTools.getConnection();
        String sql = "INSERT INTO t_user(username, password) VALUES(?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1, username);
            ps.setString(2,password);
            int i = ps.executeUpdate();

            if (i > 0){
                out.println("regist success");
            }else {
                out.println("regist fail");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTools.closeConnection(conn, ps, null);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
