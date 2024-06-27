package com.tentact.servlet;

import com.tentact.dao.PersonDao;
import com.tentact.dao.impl.PersonDaoImpl;
import com.tentact.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updatePerson")
public class UpdateServlet extends HelloServlet{

    private PersonDao personDao;

    public UpdateServlet(){
        personDao = new PersonDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Integer age = Integer.parseInt(req.getParameter("age"));
        String sex = req.getParameter("sex");

        Person person = new Person();
        person.setId(id);
        person.setAge(age);
        person.setAge(age);
        person.setName(name);
        person.setSex(sex);

        personDao.updatePerson(person);
        resp.sendRedirect("list");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
