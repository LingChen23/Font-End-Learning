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

@WebServlet("/person/*")
public class PersonServlet extends HttpServlet {


    private PersonDao personDao;
    public PersonServlet() {
        personDao = new PersonDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        处理乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //list  addPerson updatePerson loadPerson deletePerson
        ///day10/person/addPerson
        //获取请求路径
        String uri = request.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/") + 1);
        if("list".equals(path)) {
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

            out.println("<a href='../addPerson.html'>添加</a>");
        }else if("addPerson".equals(path)) {
            String name = request.getParameter("name");
            Integer age = Integer.parseInt(request.getParameter("age"));
            String sex = request.getParameter("sex");

            Person person = new Person();
            person.setName(name);
            person.setAge(age);
            person.setSex(sex);

            personDao.addPerson(person);

            //重定向
            response.sendRedirect("list");
        }else if("deletePerson".equals(path)) {
            //获取要删除的id
            Integer id = Integer.parseInt(request.getParameter("id"));
            personDao.deletePerson(id);
            response.sendRedirect("list");

        }else if("loadPerson".equals(path)) {

//        获取要修改的id
            Integer id = Integer.parseInt(request.getParameter("id"));
            Person person = personDao.findById(id);

            out.println("<form action='person/updatePerson' method='post'>");
            out.println("id:<input type='text' name='id' value='"+person.getId()+"'/><br/>");
            out.println("name:<input type='text' name='name' value='"+person.getName()+"'/><br/>");
            out.println("age:<input type='text' name='age' value='"+person.getAge()+"'/><br/>");
            out.println("sex:<input type='text' name='sex' value='"+person.getSex()+"'/><br/>");
            out.println("<input type='submit' value='修改'/>");
            out.println("</form>");

        }else if("updatePerson".equals(path)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            Integer age = Integer.parseInt(request.getParameter("age"));
            String sex = request.getParameter("sex");

            Person person = new Person();
            person.setId(id);
            person.setAge(age);
            person.setName(name);
            person.setSex(sex);
            personDao.updatePerson(person);
            response.sendRedirect("list");
        }else{
            out.println("<h1>没有这个地址。。。</h2>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
