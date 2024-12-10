
package com.mycompany.conexaojavabd.model;


public class Curso { // Classe curso
    
    //Atributos do objeto curso
    private String nomeCurso;
    private String cargaHoraria;
    
    private int idProfessor;
    private int id;

    // Métodos seletores e modificadores
    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getProfessor() {
        return idProfessor;
    }

    public void setProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Curso() {} // Construtor vazio

    public Curso(String nomeCurso, String cargaHoraria, int idProfessor, int id) {
        this.nomeCurso = nomeCurso;
        this.cargaHoraria = cargaHoraria;
        this.idProfessor = idProfessor;
        this.id = id;
    }
}