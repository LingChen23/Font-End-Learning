package com.tentact.dao;

import com.tentact.pojo.Person;

import java.util.List;

public interface PersonDao {


    void addPerson(Person person);

    void deletePerson(Integer id);


    void updatePerson(Person person);

    Person findById(Integer id);

    List<Person> showAll();
}
