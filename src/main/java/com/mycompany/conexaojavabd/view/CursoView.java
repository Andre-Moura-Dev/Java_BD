
package com.mycompany.conexaojavabd.view;

import com.mycompany.conexaojavabd.controller.CursoController;
import com.mycompany.conexaojavabd.model.Curso;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class CursoView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtId, txtNomeCurso, txtCargaHoraria, txtIdProfessor;
    private JButton btnSalvar, btnDeletar, btnListar, btnLimpar, btnAtualizar;
    private JTable tabela;
    private DefaultTableModel modelo;
    private CursoController controller;

    public CursoView(CursoController controller) {
        this.controller = controller;

        setTitle("Página Cursos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);  // Definir layout para null (posicionamento manual)

        // Painel de formulário
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(null);  // Usar layout nulo para posicionamento manual
        painelFormulario.setBounds(10, 10, 670, 180);  // Definir posição e tamanho do painel

        // ID
        JLabel lblId = new JLabel("*ID:");
        lblId.setBounds(10, 10, 80, 25);  // Posição e tamanho do rótulo
        painelFormulario.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 10, 200, 25);  // Posição e tamanho do campo de texto
        txtId.setEditable(false);
        painelFormulario.add(txtId);

        // Nome Curso
        JLabel lblCurso = new JLabel("Nome Curso:");
        lblCurso.setBounds(10, 40, 80, 25);
        painelFormulario.add(lblCurso);

        txtNomeCurso = new JTextField();
        txtNomeCurso.setBounds(100, 40, 200, 25);
        painelFormulario.add(txtNomeCurso);

        // Carga Horária
        JLabel lblCargaHoraria = new JLabel("Carga Horária:");
        lblCargaHoraria.setBounds(10, 70, 80, 25);
        painelFormulario.add(lblCargaHoraria);

        txtCargaHoraria = new JTextField();
        txtCargaHoraria.setBounds(100, 70, 200, 25);
        painelFormulario.add(txtCargaHoraria);
        
        // IdProfessor
        JLabel lblIdProfessor = new JLabel("IdProfessor:");
        lblIdProfessor.setBounds(10, 100, 80, 25);
        painelFormulario.add(lblIdProfessor);

        txtIdProfessor = new JTextField();
        txtIdProfessor.setBounds(100, 100, 200, 25);
        painelFormulario.add(txtIdProfessor);

        // Botão Salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(10, 130, 100, 30);
        painelFormulario.add(btnSalvar);

        // Botão Deletar
        btnDeletar = new JButton("Deletar");
        btnDeletar.setBounds(120, 130, 100, 30);
        painelFormulario.add(btnDeletar);

        // Botão Listar
        btnListar = new JButton("Listar");
        btnListar.setBounds(230, 130, 100, 30);
        painelFormulario.add(btnListar);
        
        // Botão Limpar
        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(340, 130, 100, 30);
        painelFormulario.add(btnLimpar);
        
        //Botão Atualizar
        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(450, 130, 100, 30);
        painelFormulario.add(btnAtualizar);

        getContentPane().add(painelFormulario);  // Adiciona o painel ao container principal

        // Tabela de dados
        modelo = new DefaultTableModel(new String[]{"ID", "Nome Curso", "Carga Horária", "IdProfessor"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(10, 200, 670, 250);  // Posição e tamanho da tabela
        getContentPane().add(scrollPane);

        // Ações dos botões
        btnSalvar.addActionListener(e -> salvarCurso());
        btnDeletar.addActionListener(e -> deletarCurso());
        btnListar.addActionListener(e -> listarCursos());
        btnLimpar.addActionListener(e -> limparCampos());
        btnAtualizar.addActionListener(e -> atualizarCurso());

        // Adicionar evento de clique na tabela para preencher os campos
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tabela.getSelectedRow();
                if (row != -1) {
                    txtId.setText(modelo.getValueAt(row, 0).toString());
                    txtNomeCurso.setText(modelo.getValueAt(row, 1).toString());
                    txtCargaHoraria.setText(modelo.getValueAt(row, 2).toString());
                    txtIdProfessor.setText(modelo.getValueAt(row, 3).toString());
                }
            }
        });

        setLocationRelativeTo(null);  // Centraliza a janela na tela
    }

    private void salvarCurso() {
        int id = txtId.getText().isEmpty() ? 0 : Integer.parseInt(txtId.getText());
        String nomeCurso = txtNomeCurso.getText();
        String cargaHoraria = txtCargaHoraria.getText();
        String idProfessor = txtIdProfessor.getText();
        Curso curso = new Curso(nomeCurso, cargaHoraria, Integer.parseInt(idProfessor), id);

        if (controller.salvar(curso)) {
            JOptionPane.showMessageDialog(this, "Curso salva com sucesso!");
            listarCursos();
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar curso!");
        }
    }

    private void deletarCurso() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um curso para deletar.");
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        int confirmacao = JOptionPane.showConfirmDialog(this, "Deseja realmente deletar o curso?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            if (controller.deletar(id)) {
                JOptionPane.showMessageDialog(this, "Curso deletado com sucesso!");
                listarCursos();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao deletar o curso!");
            }
        }
    }

    private void listarCursos() {
        modelo.setRowCount(0);
        List<Curso> cursos = controller.listar();
        for (Curso curso : cursos) {
            modelo.addRow(new Object[]{curso.getId(), curso.getNomeCurso(), curso.getCargaHoraria(), curso.getProfessor()});
        }
    }
    
    private void atualizarCurso() {
        int id = txtId.getText().isEmpty() ? 0 : Integer.parseInt(txtId.getText());
        String nomeCurso = txtNomeCurso.getText();
        String cargaHoraria = txtCargaHoraria.getText();
        String idProfessor = txtIdProfessor.getText();
        Curso curso = new Curso(nomeCurso, cargaHoraria, Integer.parseInt(idProfessor), id);

        if (controller.atualizar(curso)) {
            JOptionPane.showMessageDialog(this, "Curso atualizado com sucesso!");
            listarCursos();
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o curso!");
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNomeCurso.setText("");
        txtCargaHoraria.setText("");
        txtIdProfessor.setText("");
    }
}