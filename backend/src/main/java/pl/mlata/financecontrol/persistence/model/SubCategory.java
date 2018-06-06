package pl.mlata.financecontrol.persistence.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "sub_categories")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;

    @Column
    @NotNull
    private String name;

}
