package com.harshit.springweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshit.springweb.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
