package com.thiago.gestao_vagas.modules.company.useCases;

import com.thiago.gestao_vagas.exceptions.CompanyFoundException;
import com.thiago.gestao_vagas.modules.company.entities.CompanyEntity;
import com.thiago.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  public void execute(CompanyEntity companyEntity) {
    this.companyRepository.findByCompanyName(companyEntity.getCompanyName())
      .ifPresent(company -> {
        throw new CompanyFoundException();
      });

    this.companyRepository.save(companyEntity);
  }
}
