# Campo Minado

Um jogo de Campo Minado jogado via console.

## Sumário

- [Descrição](#descrição)
- [Instalação](#instalação)
- [Uso](#uso)
- [Estrutura do Código](#estrutura-do-código)
- [Testes](#testes)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Descrição

Este é um jogo de Campo Minado implementado em Java e jogado via console. O objetivo do jogo é abrir todos os campos que não contém minas. Você pode abrir um campo ou marcar/desmarcar um campo que você acredita conter uma mina.

## Instalação

Para rodar o jogo localmente, você precisa ter o Java Development Kit (JDK) instalado na sua máquina. 

1. Clone este repositório:
    ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
    ```

2. Compile os arquivos Java:
    ```bash
    javac -d bin src/br/com/roecker/cm/*.java src/br/com/roecker/cm/modelo/*.java src/br/com/roecker/cm/visao/*.java src/br/com/roecker/cm/excecao/*.java
    ```

3. Execute o jogo:
    ```bash
    java -cp bin br.com.roecker.cm.Aplicacao
    ```

## Uso

Ao iniciar o jogo, você verá um tabuleiro com todas as células fechadas representadas por `?`. Você pode então inserir as coordenadas da célula que deseja abrir ou marcar/desmarcar.

- Para abrir uma célula, insira `1`.
- Para marcar/desmarcar uma célula, insira `2`.

### Exemplo de jogo

```plaintext
  0  1  2  3  4  5 
0 ?  ?  ?  ?  ?  ?  
1 ?  ?  ?  ?  ?  ?  
2 ?  ?  ?  ?  ?  ?  
3 ?  ?  ?  ?  ?  ?  
4 ?  ?  ?  ?  ?  ?  
5 ?  ?  ?  ?  ?  ?  

Digite (x, y): 1, 1
1 - abrir ou 2 - (Des)marcar: 1

  0  1  2  3  4  5 
0 ?  ?  ?  ?  ?  ?  
1 ?     ?  ?  ?  ?  
2 ?  ?  ?  ?  ?  ?  
3 ?  ?  ?  ?  ?  ?  
4 ?  ?  ?  ?  ?  ?  
5 ?  ?  ?  ?  ?  ?  
