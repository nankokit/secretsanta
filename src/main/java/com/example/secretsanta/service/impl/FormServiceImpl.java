package com.example.secretsanta.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.secretsanta.cache.EntityCache;
import com.example.secretsanta.model.Form;
import com.example.secretsanta.repository.FormRepository;
import com.example.secretsanta.service.FormService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class FormServiceImpl implements FormService {

    private FormRepository formRepository;
    private EntityManager entityManager;
    private EntityCache<Form> formCache;

    @Override
    public Form createForm(Form form) {
        formCache.put(form.getId(), form);
        formRepository.save(form);
        entityManager.refresh(form);
        return form;
    }

    @Override
    public Form getFormById(Long formId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new NoSuchElementException("Form not found"));
        formCache.put(form.getId(), form);
        return form;
    }

    @Override
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    @Override
    public Form updateForm(Long id, Form updatedForm) {
        updatedForm.setId(id);
        formCache.put(id, updatedForm);
        return formRepository.save(updatedForm);
    }

    @Override
    public void deleteForm(Long id) {
        formCache.remove(id);
        formRepository.deleteById(id);
    }
}
