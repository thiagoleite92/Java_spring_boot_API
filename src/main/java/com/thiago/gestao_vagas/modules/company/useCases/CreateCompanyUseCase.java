package com.thiago.gestao_vagas.modules.company.useCases;

import com.thiago.gestao_vagas.exceptions.CompanyFoundException;
import com.thiago.gestao_vagas.modules.company.entities.CompanyEntity;
import com.thiago.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEnconder;

  public CompanyEntity execute(CompanyEntity companyEntity) {
    this.companyRepository.findByCompanyNameOrEmail(
        companyEntity.getCompanyName(),
        companyEntity.getEmail()
      )
      .ifPresent(company -> {
        throw new CompanyFoundException();
      });

    var password = passwordEnconder.encode(companyEntity.getPassword());
    companyEntity.setPassword(password);

    return this.companyRepository.save(companyEntity);
  }
}
