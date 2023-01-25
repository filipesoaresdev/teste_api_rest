package com.example.fiducial_project.services;

import com.example.fiducial_project.exception.NomeException;
import com.example.fiducial_project.model.Nome;
import com.example.fiducial_project.repository.NomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

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
     *
     * @param listNames
     */
    @Transactional
    public void insertListName(List<String> listNames) {

        listNames.forEach(nome -> {

            validaNome(nome);
            nomeRepository.save(new Nome(nome));

        });

    }

    public void validaNome(String nome) {
        if (Objects.isNull(nome) || nome.isEmpty()) {
            throw new NomeException("Nome vazio");
        }
        if (!nome.matches("[A-Z a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]+")) {
            throw new NomeException(nome + " não possui um formato válido.");
        }
        if (existsNome(nome)) {
            throw new NomeException(nome + " já cadastrado ou nomes duplicados na requisição.");
        }
    }

    /**
     * Verifica se nome existe no banco de dados
     *
     * @param nome
     * @return Boolean
     */
    public Boolean existsNome(String nome) {
        return nomeRepository.existsByNome(nome);
    }

    /**
     * Retorna todos os nomes cadastrados no banco de dados
     *
     * @return List<Nome>
     */
    public List<Nome> getAllNames() {
        return nomeRepository.findAll();
    }


}
