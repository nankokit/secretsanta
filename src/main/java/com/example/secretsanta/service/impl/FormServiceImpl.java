package com.example.secretsanta.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.secretsanta.model.Form;
import com.example.secretsanta.repository.FormRepository;
import com.example.secretsanta.service.FormService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FormServiceImpl implements FormService {

    private FormRepository formRepository;

    @Override
    public Form createForm(Form form) {
        return formRepository.save(form);
    }

    @Override
    public Form getFormById(Long formId) {
        return formRepository.findById(formId)
                .orElseThrow(() -> new NoSuchElementException("Form not found"));
    }

    @Override
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    @Override
    public Form updateForm(Long id, Form updatedForm) {
        updatedForm.setId(id);
        return formRepository.save(updatedForm);
    }

    @Override
    public void deleteForm(Long id) {
        formRepository.deleteById(id);
    }
}
