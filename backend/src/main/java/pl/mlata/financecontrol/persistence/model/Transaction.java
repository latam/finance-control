package pl.mlata.financecontrol.persistence.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Category.class)
    @JoinColumn(name = "sub_category_id")
    @NotNull
    private SubCategory subCategory;

    @Column
    @NotNull
    private LocalDateTime date;

    @Column
    @NotNull
    private BigDecimal amount;

    @Column
    private String description;

}
