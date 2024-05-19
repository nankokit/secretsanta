package com.example.secretsanta.controller;

import com.example.secretsanta.aop.RequestStats;
import com.example.secretsanta.model.Form;
import com.example.secretsanta.service.FormService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestStats
@RestController
@RequestMapping("/forms")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class FormController {
  private FormService formService;

  @PostMapping
  public ResponseEntity<Form> createForm(@RequestBody Form form) {
    return ResponseEntity.ok(formService.createForm(form));
  }

  @PutMapping("/{formId}")
  public Form updateForm(@PathVariable Long formId, @RequestBody Form form) {
    form.setId(formId);
    return formService.updateForm(formId, form);
  }

  @DeleteMapping("/{formId}")
  public void deleteForm(@PathVariable Long formId) {
    formService.deleteForm(formId);
  }

  @GetMapping("/{formId}")
  public Form getFormById(@PathVariable Long formId) {
    return formService.getFormById(formId);
  }

  @GetMapping("/getAll")
  public List<Form> getAllForms() {
    return formService.getAllForms();
  }
}
