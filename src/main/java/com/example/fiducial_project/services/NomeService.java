package com.example.fiducial_project.services;

import com.example.fiducial_project.model.Nome;
import com.example.fiducial_project.repository.NomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NomeService {

    private NomeRepository nomeRepository;

    @Autowired
    public NomeService(NomeRepository nomeRepository) {
        this.nomeRepository = nomeRepository;
    }

    @Transactional
    public void insertListName(List<String> listNames) {

        listNames.forEach(nome -> {

            if(existsNome(nome)){
                throw new RuntimeException("Nome já cadastrado ou nomes duplicados na requisição");
            }
            nomeRepository.save(new Nome(nome));

        });

    }

    public Boolean existsNome(String nome){
        return nomeRepository.existsByNome(nome);
    }

    public List<Nome> getAllNames() {
        return nomeRepository.findAll();
    }


}
