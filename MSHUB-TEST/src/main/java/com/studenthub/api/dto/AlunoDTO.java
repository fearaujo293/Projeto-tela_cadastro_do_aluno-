package com.studenthub.api.dto;

import jakarta.validation.constraints.NotNull;

public record AlunoDTO( // ADICIONAR ALGUMAS VALIDAÇÕES -> @Notnull
                        @NotNull
                        String nome,
                        @NotNull
                        String email,
                        @NotNull
                        String telefone,
                        @NotNull
                        int matricula,
                        @NotNull
                        String responsavel) {
}
