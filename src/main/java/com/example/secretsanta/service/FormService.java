package com.example.secretsanta.service;

import java.util.List;

import com.example.secretsanta.model.Form;

public interface FormService {

    public Form createForm(Form form);

    public Form getFormById(Long id);

    public List<Form> getAllForms();

    public Form updateForm(Long id, Form updatedForm);

    public void deleteForm(Long id);
}
