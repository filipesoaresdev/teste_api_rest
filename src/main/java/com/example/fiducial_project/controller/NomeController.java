package com.example.fiducial_project.controller;

import com.example.fiducial_project.exception.NomeException;
import com.example.fiducial_project.services.NomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class NomeController {

    @Autowired
    private NomeService nomeService;

    /**
     * Método responsável por receber uma lista de nomes e inserir no banco de dados
     *
     * @param nomes
     * @return ResponseEntity
     */
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listaNomes(@RequestBody List<String> nomes) {
        try {
            nomeService.inserirListaName(nomes);
            return ResponseEntity.ok("Usuários adicionados com sucesso");
        } catch (NomeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    /**
     * Método responsável por retornar todos os nomes cadastrados no banco de dados
     *
     * @return
     */
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListaNomes() {
        try {
            return ResponseEntity.ok(nomeService.getTodosNomes());
        } catch (NomeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    /**
     * Método responsável por retornar se usuário existe no banco de dados
     *
     * @return
     */
    @GetMapping(value = "/userExists", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> existeNome(@RequestParam String nome) {
        try {
            return ResponseEntity.ok(nomeService.existeNome(nome) ? true : false);
        } catch (NomeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

}
