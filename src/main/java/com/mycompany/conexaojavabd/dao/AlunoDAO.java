
package com.mycompany.conexaojavabd.dao;

import com.mycompany.conexaojavabd.model.Aluno;
import com.mycompany.conexaojavabd.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AlunoDAO {
    private Connection conexao;
    
    public AlunoDAO() {
        this.conexao = Conexao.getConnection();
    }
    
    public boolean deletar(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";
       try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
           return true;
       } catch (SQLException e) {
          e.printStackTrace();
          return false;
       }
    }
    
    public List <Aluno> listar() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aluno aluno = new Aluno(rs.getInt("matricula"), rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getInt("curso_idcurso"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return alunos;
    }
    
    public boolean salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno (matricula, curso_idcurso, nome, idade) VALUES (?, ?, ?, ?);"; 
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, aluno.getMatricula());
            stmt.setInt(2, aluno.getIdCurso());
            stmt.setString(3, aluno.getNome());
            stmt.setInt(4, aluno.getIdade());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET matricula = ?, curso_idcurso = ?, nome = ?, idade = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, aluno.getMatricula());
            stmt.setInt(2, aluno.getIdCurso());
            stmt.setString(3, aluno.getNome());
            stmt.setInt(4, aluno.getIdade());
            stmt.setInt(5, aluno.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}