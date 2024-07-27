package br.com.roecker.cm.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import br.com.roecker.cm.excecao.ExplosaoException;


public class CampoTest {

    private Campo campo;

    @BeforeEach
    void iniciaCampo(){
        campo = new Campo(3, 3);
    }
    
    @Test
    void testVizinhoOk(){
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testVizinhoFail(){
        Campo vizinho = new Campo(3, 5);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testVizinhoDiagonalOk(){
        Campo vizinho = new Campo(4, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testVizinhoDiagonalFail(){
        Campo vizinho = new Campo(5, 5);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testeAlternarMarcacaoOk(){
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoNaoMarcado(){
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoMarcarDesmarcar(){
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testAbrirNaoMinadoNaoMarcado(){
        assertTrue(campo.abrir());
    }

    @Test
    void testAbrirNaoMinadoMarcado(){
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testAbrirMinadoMarcado(){
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testAbrirMinadoNaoMarcado(){
        campo.minar();

        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbirComVizinhos(){
        Campo campo22 = new Campo(2, 2);
        Campo campo11 = new Campo(1, 1);
        Campo campo24 = new Campo(2,4);

        campo22.adicionarVizinho(campo11);
        campo.adicionarVizinho(campo22);
        campo.adicionarVizinho(campo24);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbirComVizinhosCampo11Minado(){
        Campo campo22 = new Campo(2, 2);
        Campo campo11 = new Campo(1, 1);
        Campo campo24 = new Campo(2,4);

        campo22.adicionarVizinho(campo11);
        campo.adicionarVizinho(campo22);
        campo.adicionarVizinho(campo24);
        campo11.minar();
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
        assertTrue(campo24.isAberto());
        assertFalse(campo11.isAberto());
    }

    @Test
    void testCampoSetAbertoTrue(){
        campo.setAberto(true);
        assertTrue(campo.isAberto());
    }

    @Test
    void testCampoSetAbertoFAlse(){
        campo.setAberto(false);
        assertFalse(campo.isAberto());
    }

    @Test
    void testIsFechadoOk(){
        assertTrue(campo.isFechado());
    }

    @Test
    void testIsFechadoFail(){
        campo.abrir();
        assertFalse(campo.isFechado());
    }

    @Test
    void testCampoIsMinadoOk(){
        campo.minar();
        assertTrue(campo.isMinado());
    }

    @Test
    void testCampoIsMinadoFalse(){
        assertFalse(campo.isMinado());
    }

    @Test
    void testGetLinhaOk(){
        assertEquals(campo.getLinha(), 3);
    }

    @Test
    void testGetLinhaFail(){
        assertNotEquals(campo.getLinha(), 2);
    }

    @Test
    void testGetcolunaOk(){
        assertEquals(campo.getColuna(), 3);
    }

    @Test
    void testGetColunaFail(){
        assertNotEquals(campo.getColuna(), 2);
    }

    @Test
    void testObjetivoConcluidoSemMinaOk(){
        campo.abrir();
        assertTrue(campo.objetivoAlcançado());
    }

    @Test
    void testObjetivoConcluidoSemMinaFail(){
        assertFalse(campo.objetivoAlcançado());
    }

    @Test
    void testObjetivoConcluidoComMinaOk(){
        campo.minar();
        campo.alternarMarcacao();
        assertTrue(campo.objetivoAlcançado());
    }

    @Test
    void testObjetivoConcluidoComMinaFail(){
        campo.minar();
        assertFalse(campo.objetivoAlcançado());
    }

    @Test
    void testMinasNaVizinhançaTrue(){
        Campo campo22 = new Campo(2, 2);
        campo22.minar();
        campo.adicionarVizinho(campo22);
        campo.abrir();
        assertEquals(campo.minasNaVizinhança().toString(), "1");
        
    }

    @Test
    void testMinasNaVizinhançaFalse(){
        Campo campo22 = new Campo(2, 2);
        campo.adicionarVizinho(campo22);
        campo.abrir();
        assertEquals(campo.minasNaVizinhança().toString(), "0");
    }

    @Test
    void testReiniciar(){
        campo.abrir();
        campo.minar();
        campo.alternarMarcacao();
        campo.reiniciar();
        assertFalse(campo.isAberto());
        assertFalse(campo.isMarcado());
        assertFalse(campo.isMinado());
    }

    @Test
    void testToStringFechado(){
        assertEquals(campo.toString(), "?");
    }

    @Test
    void testToStringAberto(){
        campo.abrir();
        assertEquals(campo.toString(), " ");
    }

    @Test
    void testToStringMarcado(){
        campo.alternarMarcacao();
        assertEquals(campo.toString(), "X");
    }
    
    @Test
    void testToStringAbertoMinado(){
        campo.minar();
        try {
            campo.abrir();
        } catch (ExplosaoException e) {
            e.printStackTrace();
        }
        assertEquals(campo.toString(), "*");
        
    }






}
