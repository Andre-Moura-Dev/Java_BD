
package com.mycompany.conexaojavabd.view;

import com.mycompany.conexaojavabd.controller.ProfessorController;
import com.mycompany.conexaojavabd.model.Professor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ProfessorView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtNome, txtEspecialidade, txtIdade, txtId;
    private JButton btnSalvar, btnDeletar, btnListar, btnLimpar, btnAtualizar;
    private JTable tabela;
    private DefaultTableModel modelo;
    private ProfessorController controller;

    public ProfessorView(ProfessorController controller) {
        this.controller = controller;

        setTitle("Página Professores");
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

        // Nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 40, 80, 25);
        painelFormulario.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 40, 200, 25);
        painelFormulario.add(txtNome);

        // Especialidade
        JLabel lblEspecialidade = new JLabel("Especialidade:");
        lblEspecialidade.setBounds(10, 70, 80, 25);
        painelFormulario.add(lblEspecialidade);

        txtEspecialidade = new JTextField();
        txtEspecialidade.setBounds(100, 70, 200, 25);
        painelFormulario.add(txtEspecialidade);
        
        // Idade
        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setBounds(10, 100, 80, 25);
        painelFormulario.add(lblIdade);

        txtIdade = new JTextField();
        txtIdade.setBounds(100, 100, 200, 25);
        painelFormulario.add(txtIdade);

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
        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Especialidade", "Idade"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(10, 200, 670, 250);  // Posição e tamanho da tabela
        getContentPane().add(scrollPane);

        // Ações dos botões
        btnSalvar.addActionListener(e -> salvarProfessor());
        btnDeletar.addActionListener(e -> deletarProfessor());
        btnListar.addActionListener(e -> listarProfessores());
        btnLimpar.addActionListener(e -> limparCampos());
        btnAtualizar.addActionListener(e -> atualizarProfessor());

        // Adicionar evento de clique na tabela para preencher os campos
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tabela.getSelectedRow();
                if (row != -1) {
                    txtId.setText(modelo.getValueAt(row, 0).toString());
                    txtNome.setText(modelo.getValueAt(row, 1).toString());
                    txtEspecialidade.setText(modelo.getValueAt(row, 2).toString());
                    txtIdade.setText(modelo.getValueAt(row, 3).toString());
                }
            }
        });

        setLocationRelativeTo(null);  // Centraliza a janela na tela
    }

    private void salvarProfessor() {
        int id = txtId.getText().isEmpty() ? 0 : Integer.parseInt(txtId.getText());
        String nome = txtNome.getText();
        String especialidade = txtEspecialidade.getText();
        String  idade = txtIdade.getText();
        Professor professor = new Professor(especialidade, id, nome, Integer.parseInt(idade));

        if (controller.salvar(professor)) {
            JOptionPane.showMessageDialog(this, "Pessoa salva com sucesso!");
            listarProfessores();
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar pessoa!");
        }
    }

    private void deletarProfessor() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione uma pessoa para deletar.");
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        int confirmacao = JOptionPane.showConfirmDialog(this, "Deseja realmente deletar a pessoa?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            if (controller.deletar(id)) {
                JOptionPane.showMessageDialog(this, "Pessoa deletada com sucesso!");
                listarProfessores();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao deletar pessoa!");
            }
        }
    }

    private void listarProfessores() {
        modelo.setRowCount(0);
        List<Professor> professores = controller.listar();
        for (Professor professor : professores) {
            modelo.addRow(new Object[]{professor.getId(), professor.getNome(), professor.getEspecialidade(), professor.getIdade()});
        }
    }
    
    private void atualizarProfessor() {
        int id = txtId.getText().isEmpty() ? 0 : Integer.parseInt(txtId.getText());
        String nome = txtNome.getText();
        String especialidade = txtEspecialidade.getText();
        String  idade = txtIdade.getText();
        Professor professor = new Professor(especialidade, id, nome, Integer.parseInt(idade));

        if (controller.atualizar(professor)) {
            JOptionPane.showMessageDialog(this, "Professor atualizado com sucesso!");
            listarProfessores();
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar professor!");
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtEspecialidade.setText("");
        txtIdade.setText("");
    }
}