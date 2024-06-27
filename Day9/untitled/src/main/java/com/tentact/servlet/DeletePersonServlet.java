package com.tentact.servlet;


import com.tentact.dao.PersonDao;
import com.tentact.dao.impl.PersonDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deletePerson")
public class DeletePersonServlet extends HelloServlet {

    private PersonDao personDao;

    public DeletePersonServlet(){
        personDao = new PersonDaoImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        //获取到要删除的id
        Integer id = Integer.parseInt(req.getParameter("id"));
        personDao.deletePerson(id);
        resp.sendRedirect("list");


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
