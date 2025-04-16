package com.asthana.spring_demo_01.Repository;

import org.springframework.stereotype.Repository;

import com.asthana.spring_demo_01.Entities.StudentEntity;

import org.springframework.data.jpa.repository.JpaRepository;;

@Repository
public interface StudentRepo extends JpaRepository <StudentEntity , Long> {
}
