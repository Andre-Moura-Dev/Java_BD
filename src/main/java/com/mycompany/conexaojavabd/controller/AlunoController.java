package com.mycompany.conexaojavabd.controller;

import com.mycompany.conexaojavabd.dao.AlunoDAO;
import com.mycompany.conexaojavabd.model.Aluno;
import java.util.List;


public class AlunoController {
    private AlunoDAO dao;

    // Construtor para o objeto Aluno
    public AlunoController() {
        this.dao = new AlunoDAO();
    }
    
    // Metódo listar, com uma lista de alunos
    public List<Aluno> listar() {
       return dao.listar();
    }
    
    //Método para deletar o aluno pela chave primária
    public boolean deletar(int id) {
       return dao.deletar(id); 
    }
    
    //Método para salvar os dados de cada aluno cadastrado
    public boolean salvar(Aluno aluno) {
        return dao.salvar(aluno);
    }
    
    //Método para atualizar a lista de alunos
    public boolean atualizar(Aluno aluno) {
        return dao.atualizar(aluno);
    }
}