
package com.mycompany.conexaojavabd.dao;

import com.mycompany.conexaojavabd.model.Professor;
import com.mycompany.conexaojavabd.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProfessorDAO {
    private Connection conexao;
    
    public ProfessorDAO() {
        this.conexao = Conexao.getConnection();
    }
    
    public boolean deletar(int id) {
        String sql = "DELETE FROM professor WHERE id = ?";
       try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
           return true;
       } catch (SQLException e) {
          e.printStackTrace();
          return false;
       }
    }
    
    public List <Professor> listar() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professor";
        
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Professor professor = new Professor(rs.getString("especialidade"), rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"));
                professores.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return professores;
    }

    public boolean salvar(Professor professor) {
        String sql = "INSERT INTO professor (especialidade, nome, idade) VALUES (?, ?, ?);"; 
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, professor.getEspecialidade());
            stmt.setString(2, professor.getNome());
            stmt.setInt(3, professor.getIdade());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Professor professor) {
        String sql = "UPDATE professor SET nome = ?, especialidade = ?, idade = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, professor.getEspecialidade());
            stmt.setString(2, professor.getNome());
            stmt.setInt(3, professor.getIdade());
            stmt.setInt(4, professor.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}