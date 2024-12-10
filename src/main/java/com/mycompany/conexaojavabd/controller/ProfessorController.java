
package com.mycompany.conexaojavabd.controller;

import com.mycompany.conexaojavabd.dao.ProfessorDAO;
import com.mycompany.conexaojavabd.model.Professor;
import java.util.List;


public class ProfessorController {
    private ProfessorDAO dao;

    // Construtor para o objeto Professor
    public ProfessorController() {
        this.dao = new ProfessorDAO();
    }
    
    // Metódo listar, com uma lista de professores
    public List<Professor> listar() {
       return dao.listar();
    }
    
    //Método para deletar o professor pela chave primária
    public boolean deletar(int id) {
       return dao.deletar(id); 
    }
    
    //Método para slavar os dados de cada professor cadastrado
    public boolean salvar(Professor professor) {
        return dao.salvar(professor);
    }
    
    //Método para atualizar a lista de professores
    public boolean atualizar(Professor professor) {
        return dao.atualizar(professor);
    }
}