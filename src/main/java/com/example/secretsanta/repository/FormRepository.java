package com.example.secretsanta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.secretsanta.model.Form;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

}
