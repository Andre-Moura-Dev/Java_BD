
package com.mycompany.conexaojavabd.controller;

import com.mycompany.conexaojavabd.dao.CursoDAO;
import com.mycompany.conexaojavabd.model.Curso;
import java.util.List;


public class CursoController {
    private CursoDAO dao;

    // Construtor para o objeto Curso
    public CursoController() {
        this.dao = new CursoDAO();
    }
    
    // Método listar, com uma lista de cursos
    public List<Curso> listar() {
       return dao.listar();
    }
    
    // Método para deletar o aluno pela chave primária
    public boolean deletar(int id) {
       return dao.deletar(id); 
    }
    
    // Método para salvar os dados de cada curso cadastrado
    public boolean salvar(Curso curso) {
        return dao.salvar(curso);
    }
    
    // Método para atualizar a lista de cursos 
    public boolean atualizar(Curso curso) {
        return dao.atualizar(curso);
    }
}