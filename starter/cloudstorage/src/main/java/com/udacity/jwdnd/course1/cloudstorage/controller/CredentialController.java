package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class CredentialController {

  private final CredentialService service;

  public CredentialController(CredentialService service) {
    this.service = service;
  }

  @PostMapping("/credential")
  public String saveCredential(Credential credential) {
    service.save(credential);
    return "result";
  }

  @GetMapping("/credential/{id}")
  public String deleteCredential(@PathVariable int id) {
    service.delete(id);
    return "result";
  }
}
