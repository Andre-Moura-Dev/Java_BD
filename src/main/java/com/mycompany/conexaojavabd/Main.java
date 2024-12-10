
package com.mycompany.conexaojavabd;

import com.mycompany.conexaojavabd.controller.AlunoController;
import com.mycompany.conexaojavabd.controller.CursoController;
import com.mycompany.conexaojavabd.controller.ProfessorController;
import com.mycompany.conexaojavabd.view.AlunoView;
import com.mycompany.conexaojavabd.view.CursoView;
import com.mycompany.conexaojavabd.view.ProfessorView;


public class Main {

    
    public static void main(String[] args) {
        
        // Professor
        ProfessorController controller = new ProfessorController();
        ProfessorView view = new ProfessorView(controller);
        view.setVisible(false);
        
        // Curso
        CursoController cursocontroller = new CursoController();
        CursoView cursoview = new CursoView(cursocontroller);
        cursoview.setVisible(true);
        
        // Aluno 
        AlunoController alunocontroller = new AlunoController();
        AlunoView alunoview = new AlunoView(alunocontroller);
        alunoview.setVisible(false);
    }
}