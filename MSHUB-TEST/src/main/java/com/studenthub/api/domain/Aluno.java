package com.studenthub.api.domain;

import com.studenthub.api.dto.AlunoDTO;
import com.studenthub.api.dto.PutAlunoDTO;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Entity
@Table (name="aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String telefone;
    @Column(unique = true, nullable = false)
    private int matricula;
    @Column(unique = true, nullable = false)
    private String responsavel;
    @Column(unique = true)
    private String ImagURL;
    @Column(name = "timestamp_register")
    private OffsetDateTime dateTime;


    public Aluno() {
    }



    public Aluno(UUID id, String nome, String email, String telefone, int matricula, String responsavel, String ImagURL
               ) {
       this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.matricula = matricula;
        this.responsavel = responsavel;
        this.ImagURL = ImagURL;

    }

    public Aluno(AlunoDTO dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.matricula = dto.matricula();
        this.responsavel = dto.responsavel();
    }

    public Aluno(PutAlunoDTO pdto) {
        this.nome = pdto.nome();
        this.email = pdto.email();
        this.telefone = pdto.telefone();
        this.matricula = pdto.matricula();
        this.responsavel = pdto.responsavel();
    }

    @PrePersist
    public void prePersist() {
        if (this.dateTime == null) {
            this.dateTime =OffsetDateTime.now(ZoneOffset.UTC).withOffsetSameInstant(ZoneOffset.of("-03:00"));
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getImagURL() {
        return ImagURL;
    }

    public void setImagURL(String imagURL) {
        ImagURL = imagURL;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }





}
