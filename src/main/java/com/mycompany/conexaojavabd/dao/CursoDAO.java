
package com.mycompany.conexaojavabd.dao;

import com.mycompany.conexaojavabd.model.Curso;
import com.mycompany.conexaojavabd.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CursoDAO {
    private Connection conexao;
    
    public CursoDAO() {
        this.conexao = Conexao.getConnection();
    }
    
    public boolean deletar(int id) {
        String sql = "DELETE FROM curso WHERE id = ?";
       try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
           return true;
       } catch (SQLException e) {
          e.printStackTrace();
          return false;
       }
    }
    
    public List <Curso> listar() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso";
        
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Curso curso = new Curso(rs.getString("nome_curso"), rs.getString("carga_horaria"), rs.getInt("professor_idprofessor"), rs.getInt("id"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cursos;
    }

    public boolean salvar(Curso curso) {
        String sql = "INSERT INTO curso (nome_curso, carga_horaria, professor_idprofessor) VALUES (?, ?, ?);"; 
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, curso.getNomeCurso());
            stmt.setString(2, curso.getCargaHoraria());
            stmt.setInt(3, curso.getProfessor());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Curso curso) {
        String sql = "UPDATE curso SET nome_curso = ?, carga_horaria = ?, professor_idprofessor = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, curso.getNomeCurso());
            stmt.setString(2, curso.getCargaHoraria());
            stmt.setInt(3, curso.getProfessor());
            stmt.setInt(4, curso.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}