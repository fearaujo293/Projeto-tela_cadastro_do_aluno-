package com.studenthub.api.dto;

import jakarta.validation.constraints.NotNull;

public record  PutAlunoDTO(
                           String nome,
                           String email,
                           String telefone,
                           int matricula,
                           String responsavel){
}
