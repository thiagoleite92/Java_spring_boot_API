package com.thiago.gestao_vagas.modules.candidate.useCases;

import com.thiago.gestao_vagas.modules.candidate.CandidateRepository;
import com.thiago.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateResponseDTO execute(UUID idCandidate) {
    var candidate =
      this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("User not found");
        });

    var profileCandidateResponseDTO = ProfileCandidateResponseDTO
      .builder()
      .description(candidate.getDescription())
      .username(candidate.getUsername())
      .email(candidate.getEmail())
      .name(candidate.getName())
      .id(candidate.getId())
      .build();

    return profileCandidateResponseDTO;
  }
}
