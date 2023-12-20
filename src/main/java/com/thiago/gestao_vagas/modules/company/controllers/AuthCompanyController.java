package com.thiago.gestao_vagas.modules.company.controllers;

import com.thiago.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.thiago.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

  @Autowired
  private AuthCompanyUseCase authCompanyUseCase;

  @PostMapping("/auth")
  public ResponseEntity<Object> login(
    @Valid @RequestBody AuthCompanyDTO authCompanyDTO
  ) {
    try {
      String token = this.authCompanyUseCase.execute(authCompanyDTO);

      JSONObject jsonObject = new JSONObject();

      return ResponseEntity
        .ok()
        .body(jsonObject.put("access_token", token).toString());
    } catch (Exception e) {
      return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(e.getMessage());
    }
  }
}
