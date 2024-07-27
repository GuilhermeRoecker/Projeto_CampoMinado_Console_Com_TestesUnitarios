package br.com.roecker.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.roecker.cm.excecao.ExplosaoException;

public class Campo {

    private final int linha;
    private final int coluna;

    private boolean mindado = false;
    private boolean aberto = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<>();

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    boolean adicionarVizinho(Campo vizinho) {
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaColuna + deltaLinha;

        if (deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        }
        if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else {
            return false;
        }
    }

    void alternarMarcacao() {
        if (!aberto) {
            marcado = !marcado;
        }
    }

    boolean abrir() {
        if (!aberto && !marcado) {
            aberto = true;

            if (mindado) {
                throw new ExplosaoException();
            }
            if (vizinhancaSegura()) {
                vizinhos.forEach(v -> v.abrir());
            }
            return true;
        }else{
            return false;
        }
    }

    boolean vizinhancaSegura() {
        return vizinhos.stream().noneMatch(v -> v.mindado);
    }

    void minar(){
        mindado = true;
    }

    public boolean isMarcado(){
        return marcado;
    }

    public Boolean isAberto(){
        return aberto;
    }

    void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public Boolean isFechado(){
        return !aberto;
    }

    public Boolean isMinado(){
        return mindado;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    boolean objetivoAlcançado(){
        boolean desvendado = !mindado && aberto;
        boolean protejido = mindado && marcado;
        return desvendado || protejido;
    }

    Long minasNaVizinhança(){
        return vizinhos.stream().filter(v -> v.mindado).count();
    }

    void reiniciar(){
        aberto = false;
        mindado = false;
        marcado =false;
    }

    public String toString(){
        if(marcado){
            return "X";
        } if(aberto && mindado){
            return "*";
        } if(aberto && minasNaVizinhança() > 0){
            return Long.toString(minasNaVizinhança());
        }if(aberto){
            return " ";
        } else{
            return "?";
        }
    }
}
