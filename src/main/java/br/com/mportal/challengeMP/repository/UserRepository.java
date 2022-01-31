package br.com.mportal.challengeMP.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mportal.challengeMP.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    public Optional<UserModel> findByLogin(String login);
}
