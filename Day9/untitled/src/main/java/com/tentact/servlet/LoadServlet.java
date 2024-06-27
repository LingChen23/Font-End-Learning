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

@WebServlet("/loadPerson")
public class LoadServlet extends HelloServlet {
    private PersonDao personDao;

    public LoadServlet(){
        personDao = new PersonDaoImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        //获取要修改的id
        Integer id = Integer.parseInt(req.getParameter("id"));
        Person person = personDao.findById(id);

        out.println("<form action = 'updatePerson' method = 'post'>");
        out.println("id:<input type = 'text' name = 'id' value = '"+person.getId()+"'/><br/>");
        out.println("name:<input type = 'text' name = 'name' value = '"+person.getName()+"'/><br/>");
        out.println("age:<input type = 'text' name = 'age' value = '"+person.getAge()+"'/><br/>");
        out.println("sex:<input type = 'text' name = 'sex' value = '"+person.getSex()+"'/><br/>");
        out.println("<input type = 'submit' value = '修改'/>");
        out.println("</form>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
