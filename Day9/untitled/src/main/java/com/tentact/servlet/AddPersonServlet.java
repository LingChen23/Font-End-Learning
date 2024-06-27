package com.tentact.servlet;

import com.tentact.dao.PersonDao;
import com.tentact.dao.impl.PersonDaoImpl;
import com.tentact.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addPerson")
public class AddPersonServlet extends HttpServlet {

    private PersonDao personDao;

    public AddPersonServlet(){
        personDao = new PersonDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        //获取从前台提交过来的数据
        String name = req.getParameter("name");
        Integer age =Integer.parseInt(req.getParameter("age"));
        String sex = req.getParameter("sex");

        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person.setSex(sex);

        personDao.addPerson(person);

        //重定向
        resp.sendRedirect("list");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
