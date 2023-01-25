package com.example.fiducial_project.services;


import com.example.fiducial_project.model.Nome;
import com.example.fiducial_project.repository.NomeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class NomeServiceTest {

    @InjectMocks @Spy
    private NomeService nomeService;

    @Mock
    private NomeRepository nomeRepository;

    @Test
    public void testInsertListNome() {

        nomeService.insertListName(List.of("nome1", "nome2", "nome3"));
        Mockito.verify(nomeService, Mockito.times(3)).existsNome(ArgumentMatchers.anyString());
        Mockito.verify(nomeRepository, Mockito.times(3)).save(ArgumentMatchers.any(Nome.class));
    }

    @Test(expected = RuntimeException.class)
    public void testInsertListNomeExistente() {

        Mockito.when(nomeRepository.existsByNome(ArgumentMatchers.anyString())).thenReturn(true);
        nomeService.insertListName(List.of("nome1", "nome2", "nome3"));
    }
}
