package com.tentact.dao.impl;

import com.tentact.dao.PersonDao;
import com.tentact.pojo.Person;
import com.tentact.util.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {


    @Override
    public void addPerson(Person person) {

        Connection conn = null;
        PreparedStatement ps = null;

        conn = JDBCTools.getConnection();
        String sql = "INSERT INTO t_person(NAME,age,sex) VALUES(?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,person.getName());
            ps.setInt(2,person.getAge());
            ps.setString(3,person.getSex());

             ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCTools.closeConnection(conn,ps,null);
        }



    }

    @Override
    public void deletePerson(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = JDBCTools.getConnection();
        String sql = "DELETE FROM t_person WHERE id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCTools.closeConnection(conn,ps,null);
        }


    }

    @Override
    public void updatePerson(Person person) {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = JDBCTools.getConnection();
        String sql = "UPDATE t_person SET NAME=?,age=?,sex=? WHERE id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,person.getName());
            ps.setInt(2,person.getAge());
            ps.setString(3,person.getSex());
            ps.setInt(4,person.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCTools.closeConnection(conn,ps,null);
        }
    }

    @Override
    public Person findById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = JDBCTools.getConnection();
        String sql = "SELECT id,NAME,age,sex FROM t_person WHERE id=?";
        Person person = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next()) {
                person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setAge(rs.getInt("age"));
                person.setSex(rs.getString("sex"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCTools.closeConnection(conn,ps,rs);
        }

        return person;
    }

    @Override
    public List<Person> showAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = JDBCTools.getConnection();
        String sql = "SELECT id,NAME,age,sex FROM t_person";
        List<Person> persons = new ArrayList<>();
        Person person = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setAge(rs.getInt("age"));
                person.setSex(rs.getString("sex"));
                persons.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCTools.closeConnection(conn,ps,rs);
        }

        return persons;
    }
}
