package com.thiago.gestao_vagas.modules.company.repositories;

import com.thiago.gestao_vagas.modules.company.entities.CompanyEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
  Optional<CompanyEntity> findByCompanyNameOrEmail(
    String companyName,
    String email
  );

  Optional<CompanyEntity> findByCompanyName(String companyName);
}
