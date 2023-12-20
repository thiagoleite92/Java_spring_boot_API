package com.thiago.gestao_vagas.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCandidateRequestDTO {

  private String password;
  private String username;
}
