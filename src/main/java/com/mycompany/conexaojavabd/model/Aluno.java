
package com.mycompany.conexaojavabd.model;


public class Aluno extends Pessoa { // Subclasse da classe Pessoa "herança"
    
    // Atributos da classe Aluno
    private int matricula;
    private int id;
    
    private int idCurso;

    // Métodos seletores e modificadores
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
    
    public Aluno(){} // Construtor vazio

    public Aluno(int matricula, int id, String nome, int idade, int idCurso) {
        super(nome, idade);
        this.matricula = matricula;
        this.id = id;
        this.idCurso = idCurso;
    }
}