package com.example.fiducial_project.repository;

import com.example.fiducial_project.model.Nome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NomeRepository extends JpaRepository<Nome, String> {

    public boolean existsByNome(String nome);

}
