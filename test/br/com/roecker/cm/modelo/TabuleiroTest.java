package br.com.roecker.cm.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.roecker.cm.excecao.ExplosaoException;

public class TabuleiroTest {

    private Tabuleiro tabuleiro;

    private Campo campoPrincipal;
    private Set<Campo> campos;


    @Test
    void inicializaTabuleiro(){
        tabuleiro = new Tabuleiro(6, 6, 6);
    }


    
}