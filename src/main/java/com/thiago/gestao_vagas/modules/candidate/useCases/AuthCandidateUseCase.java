package com.thiago.gestao_vagas.modules.candidate.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.thiago.gestao_vagas.modules.candidate.CandidateRepository;
import com.thiago.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.thiago.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthCandidateUseCase {

  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCandidateResponseDTO execute(
    AuthCandidateRequestDTO authCandidateRequestDTO
  ) throws AuthenticationException {
    var candidate =
      this.candidateRepository.findByUsername(
          authCandidateRequestDTO.getUsername()
        )
        .orElseThrow(() -> {
          throw new UsernameNotFoundException(
            "Username or password not matches."
          );
        });

    var passwordMatches =
      this.passwordEncoder.matches(
          authCandidateRequestDTO.getPassword(),
          candidate.getPassword()
        );

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var expiresIn = Instant.now().plus(Duration.ofMinutes(10));

    var token = JWT
      .create()
      .withIssuer("javagas")
      .withSubject(candidate.getId().toString())
      .withExpiresAt(expiresIn)
      .withClaim("roles", Arrays.asList("CANDIDATE"))
      .sign(algorithm);

    var authCandidateResponseDTO = AuthCandidateResponseDTO
      .builder()
      .access_token(token)
      .expires_in(expiresIn.toEpochMilli())
      .build();

    return authCandidateResponseDTO;
  }
}
