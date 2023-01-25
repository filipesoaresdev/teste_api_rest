package com.example.fiducial_project.controller;

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
     * @param nomes
     * @return ResponseEntity
     */
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listNomes(@RequestBody List<String> nomes) {
        try {
            nomeService.insertListName(nomes);
            return ResponseEntity.ok("Usuários adicionados com sucesso");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e);
        }
    }

    /**
     * Método responsável por retornar todos os nomes cadastrados no banco de dados
     * @return
     */
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListNomes() {
        try {
            nomeService.getAllNames();
            return ResponseEntity.ok("Usuários adicionados com sucesso");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e);
        }
    }

    /**
     * Método responsável por retornar se usuário existe no banco de dados
     * @return
     */
    @GetMapping(value = "/userExists", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> existsNome(@RequestParam String nome) {
        try {
            return ResponseEntity.ok(nomeService.existsNome(nome)?true:false);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e);
        }
    }

}
