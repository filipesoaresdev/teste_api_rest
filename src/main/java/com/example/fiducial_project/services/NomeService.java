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

    /**
     * Insere uma lista de nomes no banco de dados
     * Caso algum nome já exista, lança uma exceção
     * @param listNames
     */
    @Transactional
    public void insertListName(List<String> listNames) {

        listNames.forEach(nome -> {

            if(existsNome(nome)){
                throw new RuntimeException("Nome já cadastrado ou nomes duplicados na requisição");
            }
            nomeRepository.save(new Nome(nome));

        });

    }

    /**
     * Verifica se nome existe no banco de dados
     * @param nome
     * @return Boolean
     */
    public Boolean existsNome(String nome){
        return nomeRepository.existsByNome(nome);
    }

    /**
     * Retorna todos os nomes cadastrados no banco de dados
     * @return List<Nome>
     */
    public List<Nome> getAllNames() {
        return nomeRepository.findAll();
    }


}
