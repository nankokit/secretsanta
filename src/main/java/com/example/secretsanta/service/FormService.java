package com.example.secretsanta.service;

import com.example.secretsanta.model.Form;
import java.util.List;

public interface FormService {

  public Form createForm(Form form);

  public Form getFormById(Long id);

  public List<Form> getAllForms();

  public Form updateForm(Long id, Form updatedForm);

  public void deleteForm(Long id);
}
