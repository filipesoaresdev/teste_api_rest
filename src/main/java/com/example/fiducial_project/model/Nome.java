package com.example.fiducial_project.model;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "nomes")
public class Nome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(unique = true, nullable = false)
    private String nome;

    public Nome() {
    }

    public Nome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
