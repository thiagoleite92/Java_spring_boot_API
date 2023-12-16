package com.thiago.gestao_vagas.modules.candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CandidateEntity {

  private UUID id;
  private String name;

  @Pattern(
    regexp = "\\S+",
    message = "O campo [username] não deve conter espaços."
  )
  private String username;

  @Email(message = "O campo [email] deve conter um e-mail válido.")
  private String email;

  @Length(
    min = 5,
    max = 10,
    message = "A senha deve conter entre 5 e 10 caracteres."
  )
  private String password;

  private String description;
  private String curriculum;
}
