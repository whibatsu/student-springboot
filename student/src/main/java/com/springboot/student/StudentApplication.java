package com.springboot.student;

import com.springboot.student.dao.StudentDAO;
import com.springboot.student.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){

		return runner -> {
			createStudent(studentDAO);

			createMultipleStudents(studentDAO);

			readStudent(studentDAO);

			updateStudent(studentDAO);

			removeStudent(studentDAO);
		};
	}

	private void removeStudent(StudentDAO studentDAO) {
		Integer id = 1;
		boolean isSuccess = studentDAO.removeById(id);
		System.out.println("Removing student with id " + id + ": "+isSuccess);
	}

	private void updateStudent(StudentDAO studentDAO) {
		Integer id = 1;
		Student student = new Student("btsuu", "yerimm", "yerimun@mailinator.com");
		student.setId(id);
		boolean isSuccess = studentDAO.updateById(student);
		System.out.println("Updating student with id " + id + ": "+isSuccess);
	}

	private void readStudent(StudentDAO studentDAO) {
		Integer id = 1;
		Student student = studentDAO.findById(id);
		if(null != student){
			System.out.println("Retrieving student with id " + id + ": "+student.getFirstName());
		} else {
			System.out.println("Failed retrieving student with id " + id);
		}
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating multiple new student object . . .");
		for(int i=1;i<=3;i++){
			System.out.println("Creating new student object . . .");
			Student student = new Student("btsu"+i, "yerim"+i, "yerim"+i+"@mailinator.com");

			System.out.println("Saving student . . .");
			studentDAO.save(student);

			System.out.println("Saved student. Generated Id: " + student.getId());
		}
	}

	private void createStudent(StudentDAO studentDAO) {

		System.out.println("Creating new student object . . .");
		Student student = new Student("btsu", "yerim", "yerim@mailinator.com");

		System.out.println("Saving student . . .");
		studentDAO.save(student);

		System.out.println("Saved student. Generated Id: " + student.getId());
	}
}
