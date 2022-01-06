package com.harshit.springweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.harshit.springweb.entities.Student;
import com.harshit.springweb.repos.StudentRepository;

@RestController
public class StudentRestController {

	@Autowired
	StudentRepository repository;

	@RequestMapping(value = "/student/", method = RequestMethod.GET)
	public List<Student> getStudents() {
		return repository.findAll();
	}

	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable int id) {
		return repository.getById(id);
	}

	@RequestMapping(value = "/student/", method = RequestMethod.POST)
	public Student createStudent(@RequestBody Student student) {
		return repository.save(student);
	}

	@RequestMapping(value = "/student/", method = RequestMethod.PUT)
	public Student updateStudent(@RequestBody Student student) {
		return repository.save(student);
	}

	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable int id) {
		repository.deleteById(id);
	}

}
