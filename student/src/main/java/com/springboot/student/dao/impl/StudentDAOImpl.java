package com.springboot.student.dao.impl;

import com.springboot.student.dao.StudentDAO;
import com.springboot.student.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * The class StudentDAOImpl
 *
 * @author Whibatsu H.
 */
@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;

    @Autowired //used only when theres more than 1 constructor
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student){
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id){
        return entityManager.find(Student.class, id);
    }

    @Override
    @Transactional
    public boolean updateById(Student student){
        Student oldStudent = findById(student.getId());
        if(null != oldStudent){
            oldStudent.setFirstName(student.getFirstName());
            oldStudent.setLastName(student.getLastName());
            oldStudent.setEmail(student.getEmail());
            entityManager.merge(oldStudent);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    @Transactional
    public boolean removeById(Integer id){
        Student student = findById(id);
        if(null != student){
            entityManager.remove(student);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
