
package com.mycompany.conexaojavabd.model;


public class Professor extends Pessoa {
    private String especialidade;
    private int id;

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Professor(){}

    public Professor(String especialidade, int id, String nome, int idade) {
        super(nome, idade);
        this.especialidade = especialidade;
        this.id = id;
    }
}