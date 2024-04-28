package com.example.secretsanta.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.secretsanta.model.Form;
import com.example.secretsanta.service.FormService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class FormControllerTest {
  @Mock private FormService formService;

  @InjectMocks private FormController formController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateForm() {
    // Arrange
    Form form = new Form();
    when(formService.createForm(any(Form.class))).thenReturn(form);

    // Act
    ResponseEntity<Form> response = formController.createForm(form);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(form, response.getBody());
    verify(formService).createForm(any(Form.class));
  }

  @Test
  void testUpdateForm() {
    // Arrange
    Long formId = 1L;
    Form form = new Form();
    form.setId(formId);
    when(formService.updateForm(formId, form)).thenReturn(form);

    // Act
    Form updatedForm = formController.updateForm(formId, form);

    // Assert
    assertNotNull(updatedForm);
    assertEquals(form, updatedForm);
    verify(formService).updateForm(formId, form);
  }

  @Test
  void testDeleteForm() {
    // Arrange
    Long formId = 1L;

    // Act
    formController.deleteForm(formId);

    // Assert
    verify(formService).deleteForm(formId);
  }

  @Test
  void testGetFormById() {
    // Arrange
    Long formId = 1L;
    Form form = new Form();
    when(formService.getFormById(formId)).thenReturn(form);

    // Act
    Form retrievedForm = formController.getFormById(formId);

    // Assert
    assertNotNull(retrievedForm);
    assertEquals(form, retrievedForm);
    verify(formService).getFormById(formId);
  }

  @Test
  void testGetAllForms() {
    // Arrange
    List<Form> forms = new ArrayList<>();
    forms.add(new Form());
    when(formService.getAllForms()).thenReturn(forms);

    // Act
    List<Form> retrievedForms = formController.getAllForms();

    // Assert
    assertNotNull(retrievedForms);
    assertEquals(forms, retrievedForms);
    verify(formService).getAllForms();
  }
}