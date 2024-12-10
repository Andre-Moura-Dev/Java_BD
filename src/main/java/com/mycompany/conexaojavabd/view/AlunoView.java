
package com.mycompany.conexaojavabd.view;

import com.mycompany.conexaojavabd.controller.AlunoController;
import com.mycompany.conexaojavabd.model.Aluno;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AlunoView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtId, txtMatricula, txtIdCurso, txtNome, txtIdade;
    private JButton btnSalvar, btnDeletar, btnListar, btnLimpar, btnAtualizar;
    private JTable tabela;
    private DefaultTableModel modelo;
    private AlunoController controller;

    public AlunoView(AlunoController controller) {
        this.controller = controller;

        setTitle("Página Alunos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);  // Definir layout para null (posicionamento manual)

        // Painel de formulário
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(null);  // Usar layout nulo para posicionamento manual
        painelFormulario.setBounds(10, 10, 670, 210);  // Definir posição e tamanho do painel

        // ID
        JLabel lblId = new JLabel("*ID:");
        lblId.setBounds(10, 10, 80, 25);  // Posição e tamanho do rótulo
        painelFormulario.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 10, 200, 25);  // Posição e tamanho do campo de texto
        txtId.setEditable(false);
        painelFormulario.add(txtId);

        // Matricula
        JLabel lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setBounds(10, 40, 80, 25);
        painelFormulario.add(lblMatricula);

        txtMatricula = new JTextField();
        txtMatricula.setBounds(100, 40, 200, 25);
        painelFormulario.add(txtMatricula);

        // Id Curso
        JLabel lblIdCurso = new JLabel("IdCurso:");
        lblIdCurso.setBounds(10, 70, 80, 25);
        painelFormulario.add(lblIdCurso);

        txtIdCurso = new JTextField();
        txtIdCurso.setBounds(100, 70, 200, 25);
        painelFormulario.add(txtIdCurso);
        
        // Nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 100, 80, 25);
        painelFormulario.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 100, 200, 25);
        painelFormulario.add(txtNome);
        
        // Idade
        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setBounds(10, 130, 80, 25);
        painelFormulario.add(lblIdade);

        txtIdade = new JTextField();
        txtIdade.setBounds(100, 130, 200, 25);
        painelFormulario.add(txtIdade);

        // Botão Salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(10, 160, 100, 30);
        painelFormulario.add(btnSalvar);

        // Botão Deletar
        btnDeletar = new JButton("Deletar");
        btnDeletar.setBounds(120, 160, 100, 30);
        painelFormulario.add(btnDeletar);

        // Botão Listar
        btnListar = new JButton("Listar");
        btnListar.setBounds(230, 160, 100, 30);
        painelFormulario.add(btnListar);
        
        // Botão Limpar
        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(340, 160, 100, 30);
        painelFormulario.add(btnLimpar);
        
        //Botão Atualizar
        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(450, 160, 100, 30);
        painelFormulario.add(btnAtualizar);

        getContentPane().add(painelFormulario);  // Adiciona o painel ao container principal

        // Tabela de dados
        modelo = new DefaultTableModel(new String[]{"ID", "Matricula", "IdCurso", "nome", "idade"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(10, 230, 670, 250);  // Posição e tamanho da tabela
        getContentPane().add(scrollPane);

        // Ações dos botões
        btnSalvar.addActionListener(e -> salvarAluno());
        btnDeletar.addActionListener(e -> deletarAluno());
        btnListar.addActionListener(e -> listarAlunos());
        btnLimpar.addActionListener(e -> limparCampos());
        btnAtualizar.addActionListener(e -> atualizarAluno());

        // Adicionar evento de clique na tabela para preencher os campos
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tabela.getSelectedRow();
                if (row != -1) {
                    txtId.setText(modelo.getValueAt(row, 0).toString());
                    txtMatricula.setText(modelo.getValueAt(row, 1).toString());
                    txtIdCurso.setText(modelo.getValueAt(row, 2).toString());
                    txtNome.setText(modelo.getValueAt(row, 3).toString());
                    txtIdade.setText(modelo.getValueAt(row, 4).toString());
                }
            }
        });

        setLocationRelativeTo(null);  // Centraliza a janela na tela
    }

    private void salvarAluno() {
        int id = txtId.getText().isEmpty() ? 0 : Integer.parseInt(txtId.getText());
        String matricula = txtMatricula.getText();
        String idCurso = txtIdCurso.getText();
        String nome = txtNome.getText();
        String idade = txtIdade.getText();
        Aluno aluno = new Aluno(Integer.parseInt(matricula), id, nome, Integer.parseInt(idade), Integer.parseInt(idCurso));

        if (controller.salvar(aluno)) {
            JOptionPane.showMessageDialog(this, "Aluno salvo com sucesso!");
            listarAlunos();
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar aluno!");
        }
    }

    private void deletarAluno() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno para deletar.");
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        int confirmacao = JOptionPane.showConfirmDialog(this, "Deseja realmente deletar o aluno?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            if (controller.deletar(id)) {
                JOptionPane.showMessageDialog(this, "aluno deletado com sucesso!");
                listarAlunos();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao deletar o aluno!");
            }
        }
    }

    private void listarAlunos() {
        modelo.setRowCount(0);
        List<Aluno> alunos = controller.listar();
        for (Aluno aluno : alunos) {
            modelo.addRow(new Object[]{aluno.getId(), aluno.getMatricula(), aluno.getIdCurso(), aluno.getNome(), aluno.getIdade()});
        }
    }
    
    private void atualizarAluno() {
        int id = txtId.getText().isEmpty() ? 0 : Integer.parseInt(txtId.getText());
        String matricula = txtMatricula.getText();
        String idCurso = txtIdCurso.getText();
        String nome = txtNome.getText();
        String idade = txtIdade.getText();
        Aluno aluno = new Aluno(Integer.parseInt(matricula), id, nome, Integer.parseInt(idade), Integer.parseInt(idCurso));

        if (controller.atualizar(aluno)) {
            JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!");
            listarAlunos();
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o aluno!");
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtMatricula.setText("");
        txtIdCurso.setText("");
        txtNome.setText("");
        txtIdade.setText("");
    }
}