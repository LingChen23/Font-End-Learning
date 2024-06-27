package com.tentact.servlet;

import com.tentact.dao.PersonDao;
import com.tentact.dao.impl.PersonDaoImpl;
import com.tentact.pojo.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/list")
public class ServletListPersonServlet extends HttpServlet {

    private PersonDao personDao;

    public ServletListPersonServlet() {
        personDao = new PersonDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        List<Person> persons = personDao.showAll();


        out.println("<table width='500px' height='300px' border='1px' cellspacing='0'>");
        out.println("<tr><td>id</td><td>name</td><td>age</td><td>sex</td><td>操作</td></tr>");

        for(Person person : persons) {
            out.println("<tr>");
            out.println("<td>" + person.getId() + "</td>");
            out.println("<td>" + person.getName() + "</td>");
            out.println("<td>" + person.getAge() + "</td>");
            out.println("<td>" + person.getSex() + "</td>");
            out.println("<td>");
            out.println("<a href='deletePerson?id="+person.getId()+"' onclick='return confirm(\"是否删除\")'>删除</a>");
            out.println("<a href='loadPerson?id="+person.getId()+"'>编辑</a>");
            out.println("</td>");
            out.println("</tr>");
        }

        out.println("</table>");


        out.println("<a href = 'addPerson.html'>添加</a>");




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
