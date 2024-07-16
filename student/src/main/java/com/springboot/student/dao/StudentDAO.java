package com.springboot.student.dao;

import com.springboot.student.entity.Student;

/**
 * The class Student
 *
 * @author Whibatsu H.
 */
public interface StudentDAO {

    void save(Student student);

    Student findById(Integer id);

    boolean updateById(Student student);

    boolean removeById(Integer id);
}
