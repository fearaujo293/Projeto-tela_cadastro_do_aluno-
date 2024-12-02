package com.studenthub.api.repository;

import com.studenthub.api.domain.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.annotations.processing.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {

    @Query(value = "SELECT * FROM aluno ORDER BY timestamp_register DESC LIMIT 1;", nativeQuery = true)
    Aluno findLastStudent();

}
