package br.com.roecker.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.roecker.cm.excecao.ExplosaoException;
import br.com.roecker.cm.excecao.SairException;
import br.com.roecker.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        excutarJogo();
    }

    private void excutarJogo() {
        try {
            boolean continuar = true;
            while (continuar) {
                cicloDoJogo();
                System.out.println("Outra partida?? (S/n) ");
                String resposta = entrada.nextLine();
                if("n".equalsIgnoreCase(resposta)){
                    continuar= false;
                }else{
                    tabuleiro.reiniciar();
                }
            }
        } catch (SairException e) {
            System.out.println("Tchau!!!!!!");
        }finally{
            entrada.close();
        }
    }

    private void cicloDoJogo() {
        try {
            while (!tabuleiro.objetivoAlcançado()){
                System.out.println(tabuleiro);
                String digitado = capturarValorDigitado("Digite (x, y): ");

                try {
                    Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                    .map(e -> Integer.parseInt(e.trim())).iterator();
    
                    digitado =  capturarValorDigitado("1 - abrir ou 2  - (Des)marcar ");
    
                    if("1".equals(digitado)){
                        tabuleiro.abrir(xy.next(), xy.next());
                    }if("2".equals(digitado)){
                        tabuleiro.alternarMarcacao(xy.next(), xy.next());
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("O valor ddigitado não é um numero\n\n");
                }

            }
            System.out.println(tabuleiro);
            System.out.println("Você ganhou!!!!!");
        } catch (ExplosaoException e) {
            System.out.println(tabuleiro);
            System.out.println("Você Perdeu");
        }
    }
    
    private String capturarValorDigitado(String texto){
        System.out.print(texto);
        String digitado = entrada.nextLine();

        if("sair".equalsIgnoreCase(digitado)){
            throw new SairException();
        }

        return digitado;
    }
}