package com.example.secretsanta.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.secretsanta.cache.EntityCache;
import com.example.secretsanta.model.Form;
import com.example.secretsanta.repository.FormRepository;
import com.example.secretsanta.service.impl.FormServiceImpl;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FormServiceImplTest {

  @Mock private FormRepository formRepository;

  @Mock private EntityManager entityManager;

  @Mock private EntityCache<Form> formCache;

  @InjectMocks private FormServiceImpl formService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateForm() {
    // Arrange
    Form form = new Form();
    form.setId(1L);

    when(formRepository.save(form)).thenReturn(form);

    // Act
    Form createdForm = formService.createForm(form);

    // Assert
    assertNotNull(createdForm);
    assertEquals(form.getId(), createdForm.getId());

    verify(formCache).put(form.getId(), form);
    verify(formRepository).save(form);
    verify(entityManager).refresh(form);
  }

  @Test
  void testGetFormById() {
    // Arrange
    Long formId = 1L;
    Form form = new Form();
    form.setId(formId);

    when(formRepository.findById(formId)).thenReturn(Optional.of(form));

    // Act
    Form retrievedForm = formService.getFormById(formId);

    // Assert
    assertNotNull(retrievedForm);
    assertEquals(formId, retrievedForm.getId());

    verify(formRepository).findById(formId);
    verify(formCache).put(form.getId(), form);
  }

  @Test
  void testGetFormByIdNotFound() {
    // Arrange
    Long formId = 1L;

    when(formRepository.findById(formId)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(NoSuchElementException.class, () -> formService.getFormById(formId));

    verify(formRepository).findById(formId);
  }

  @Test
  void testGetAllForms() {
    // Arrange
    List<Form> forms = new ArrayList<>();
    forms.add(new Form());
    forms.add(new Form());

    when(formRepository.findAll()).thenReturn(forms);

    // Act
    List<Form> retrievedForms = formService.getAllForms();

    // Assert
    assertNotNull(retrievedForms);
    assertEquals(forms.size(), retrievedForms.size());

    verify(formRepository).findAll();
  }

  @Test
  void testUpdateForm() {
    // Arrange
    Long formId = 1L;
    Form updatedForm = new Form();
    updatedForm.setId(formId);

    when(formRepository.save(updatedForm)).thenReturn(updatedForm);

    // Act
    Form updated = formService.updateForm(formId, updatedForm);

    // Assert
    assertNotNull(updated);
    assertEquals(formId, updated.getId());

    verify(formCache).put(formId, updatedForm);
    verify(formRepository).save(updatedForm);
  }

  @Test
  void testDeleteForm() {
    // Arrange
    Long formId = 1L;

    // Act
    formService.deleteForm(formId);

    // Assert
    verify(formCache).remove(formId);
    verify(formRepository).deleteById(formId);
  }
}