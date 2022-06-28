package br.com.projeto.biblioteca.dto;

import lombok.Data;

@Data
public class LoanRegister {
    private Long id;
    private Boolean lending;
    private Boolean retired;
}
