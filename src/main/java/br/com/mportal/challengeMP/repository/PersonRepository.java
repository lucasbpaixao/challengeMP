package br.com.mportal.challengeMP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mportal.challengeMP.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByOrderByNameAsc();
}
