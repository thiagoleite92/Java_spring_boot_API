package com.thiago.gestao_vagas.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.thiago.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.thiago.gestao_vagas.modules.company.repositories.CompanyRepository;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthCompanyUseCase {

  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthCompanyDTO authCompanyDTO)
    throws AuthenticationException {
    var company =
      this.companyRepository.findByCompanyName(authCompanyDTO.getCompanyName())
        .orElseThrow(() -> {
          throw new UsernameNotFoundException(
            "Company or Password not matches"
          );
        });

    var passwordMatches =
      this.passwordEncoder.matches(
          authCompanyDTO.getPassword(),
          company.getPassword()
        );

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var token = JWT
      .create()
      .withIssuer("javagas")
      .withSubject(company.getId().toString())
      .sign(algorithm);

    return token;
  }
}
