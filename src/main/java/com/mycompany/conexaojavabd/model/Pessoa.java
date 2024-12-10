
package com.mycompany.conexaojavabd.model;


public abstract class Pessoa { // Classe abstrata
    private String nome;
    private int idade;

    // Métodos seletores e modificadores
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    public Pessoa() {} // Construtor vazio

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
}