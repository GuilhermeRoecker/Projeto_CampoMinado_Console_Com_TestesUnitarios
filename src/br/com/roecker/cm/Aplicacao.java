package br.com.roecker.cm;

import br.com.roecker.cm.modelo.Tabuleiro;
import br.com.roecker.cm.visao.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(6, 6 ,3);

        new TabuleiroConsole(tabuleiro);
    }
}
