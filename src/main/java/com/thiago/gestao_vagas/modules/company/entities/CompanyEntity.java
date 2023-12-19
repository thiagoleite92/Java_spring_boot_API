package com.thiago.gestao_vagas.modules.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

@Data
@Entity(name = "company")
public class CompanyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank
  @Pattern(
    regexp = "\\S+",
    message = "O campo [companyName] não deve conter espaços."
  )
  private String companyName;

  @Email(message = "O campo [email] deve conter um e-mail válido.")
  private String email;

  @Length(min = 5, message = "A senha deve conter entre 5 e 10 caracteres.")
  private String password;

  private String website;
  private String description;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
