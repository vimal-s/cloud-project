package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
//    return "redirect:http://localhost:8080/home";
    return "result";
  }

  @GetMapping("/credential/{id}") // todo: change this to delete mapping somehow
  public String deleteCredential(@PathVariable int id) {
    service.delete(id);
//    return "redirect:http://localhost:8080/home";
    return "result";
  }
}
