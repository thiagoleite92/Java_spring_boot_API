package com.thiago.gestao_vagas.modules.candidate;

import com.thiago.gestao_vagas.modules.candidate.entities.CandidateEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository
  extends JpaRepository<CandidateEntity, UUID> {
  Optional<CandidateEntity> findByUsernameOrEmail(
    String username,
    String email
  );

  Optional<CandidateEntity> findByUsername(String username);
}
