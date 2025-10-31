package com.lorenzo.GestaoFinanceira.model;

import com.lorenzo.GestaoFinanceira.enums.Categoria;
import com.lorenzo.GestaoFinanceira.enums.TipoTransacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Descricao e obrigatoria")
    @NotBlank(message = "Descricao nao pode estar vazia")
    @Size(min = 3, max = 100, message = "Descricao deve ter entre 3 e 100 caracteres")
    @Column
    private String descricao;

    @NotNull(message = "Valor e obrigatorio")
    @Positive(message = "Valor deve ser positivo")
    @DecimalMin(value = "0.01", message = "Valor minimo e de R$0,01")
    @DecimalMax(value = "999999.99", message = "Valor maximo e de R$999.999,99")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @NotNull(message = "Tipo e obrigatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransacao tipo;

    @NotNull(message = "Categoria e obrigatoria")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @NotNull(message = "Data e obrigatoria")
    @Column(nullable = false)
    private LocalDate data;

    @Size(max = 500, message = "Observacao deve ter no maximo 500 caracteres")
    @Column(length = 500)
    private String observacao;

    @Column(name = "criado_em", updatable = false)
    private LocalDate criadoEm;

    @PrePersist
    private void onCreate() {
        this.criadoEm = LocalDate.now();
    }
}
