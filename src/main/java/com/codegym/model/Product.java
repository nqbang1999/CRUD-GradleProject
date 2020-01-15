package com.codegym.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeProduct typeProduct;

    @NotNull
    private int price;

    private LocalDate dateCreate;

    @NotEmpty
    private String description;

    public Product() {
    }

    public Product(@NotEmpty String name, TypeProduct typeProduct, @NotNull int price, LocalDate dateCreate, @NotEmpty String description) {
        this.name = name;
        this.typeProduct = typeProduct;
        this.price = price;
        this.dateCreate = dateCreate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
