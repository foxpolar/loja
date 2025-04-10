package com.mycommerce.wellcommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;


@Entity(name = "categories")
@NoArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoryId;
    @Column(unique = true)
    @NotEmpty(message = "{categories.categoryName.required}")
    private String categoryName;
}
