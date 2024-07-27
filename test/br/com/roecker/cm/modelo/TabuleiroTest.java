package br.com.roecker.cm.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.com.roecker.cm.excecao.ExplosaoException;

public class TabuleiroTest {

    private Tabuleiro tabuleiro;

    @BeforeEach
    void inicializaTabuleiro() {
        tabuleiro = new Tabuleiro(6, 6, 6);
    }

    @Test
    void testAbrirCampoSemMina() {
        tabuleiro.reiniciar();
        assertDoesNotThrow(() -> {
            tabuleiro.abrir(1, 1);
        });
        assertTrue(tabuleiro.objetivoAlcançado() || !tabuleiro.campos.stream()
                .anyMatch(c -> c.getLinha() == 1 && c.getColuna() == 1 && c.isFechado()));
    }

    @Test
    void testAbrirCampoComMina() {
        tabuleiro = new Tabuleiro(1, 1, 1);
        assertThrows(ExplosaoException.class, () -> {
            tabuleiro.abrir(0, 0);
        });
    }

    @Test
    void testAbrirCampoJaMarcado() {
        tabuleiro.alternarMarcacao(2, 2);
        tabuleiro.abrir(2, 2);
        assertTrue(tabuleiro.campos.stream().anyMatch(c -> c.getLinha() == 2 && c.getColuna() == 2 && c.isFechado()));
    }

    @Test
    void testAbrirCampoJaAberto() {

        try {
            tabuleiro.abrir(3, 3);
        } catch (ExplosaoException e) {
            
        }

        boolean estadoAntes = tabuleiro.campos.stream()
                .anyMatch(c -> c.getLinha() == 3 && c.getColuna() == 3 && c.isAberto());
        try {
            tabuleiro.abrir(3, 3);
        } catch (ExplosaoException e) {
            
        }
        boolean estadoDepois = tabuleiro.campos.stream()
                .anyMatch(c -> c.getLinha() == 3 && c.getColuna() == 3 && c.isAberto());
        assertTrue(estadoAntes && estadoDepois);
    }

    @Test
    void testReinicir() {

        tabuleiro.abrir(0, 0);

        tabuleiro.reiniciar();
        boolean resultado = tabuleiro.campos.stream().allMatch(c -> c.isFechado());
        boolean resultado2 = tabuleiro.campos.stream().allMatch(c -> c.isMarcado());
        assertTrue(resultado);
        assertFalse(resultado2);
    }

    @Test
    void testToStringTabuleiroAposAbrir() {
        tabuleiro.reiniciar();
        tabuleiro.campos.stream().filter(c -> c.getLinha() == 1 && c.getColuna() == 1)
        .forEach(Campo::minar);
        tabuleiro.abrir(0, 0); 



        String expected = 
        "   0  1  2  3  4  5 \n" +
        "0  1  ?  ?  ?  ?  ? \n" +
        "1  ?  ?  ?  ?  ?  ? \n" +
        "2  ?  ?  ?  ?  ?  ? \n" +
        "3  ?  ?  ?  ?  ?  ? \n" +
        "4  ?  ?  ?  ?  ?  ? \n" +
        "5  ?  ?  ?  ?  ?  ? \n";

        assertEquals(expected, tabuleiro.toString());
    }

    @Test
    void testToStringTabuleiroComMarcacao() {
        tabuleiro.alternarMarcacao(1, 1);

        String expected = 
        "   0  1  2  3  4  5 \n" +
        "0  ?  ?  ?  ?  ?  ? \n" +
        "1  ?  X  ?  ?  ?  ? \n" +
        "2  ?  ?  ?  ?  ?  ? \n" +
        "3  ?  ?  ?  ?  ?  ? \n" +
        "4  ?  ?  ?  ?  ?  ? \n" +
        "5  ?  ?  ?  ?  ?  ? \n";

        assertEquals(expected, tabuleiro.toString());
    }
}