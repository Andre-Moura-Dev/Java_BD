
package com.mycompany.conexaojavabd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexao { // Classe conexão
   //protected static String driver = "com.mysql.jdbc.Driver";
   
    //Conexão Estabelecida entre o java e o banco de dados
   private static final String URL = "jdbc:mysql://localhost:3306/mydb";
   private static final String USER = "root";
   private static final String PASSWORD = "";
   private static Connection conexao;
   
   //Class.forName(driver);
   
   private Conexao() {
       // Previne a criação de instâncias
   }
    
    //Método que verifica se a conexão foi bem estabalecida entre o java e o banco
    public static Connection getConnection() {
        if(conexao == null) {
            try {
                conexao = DriverManager.getConnection(URL, USER, PASSWORD); // Conexão Estabelecida
            } catch (SQLException e) {
                  e.printStackTrace();
                throw new RuntimeException("Erro ao conectar ao banco de dados", e); // Erro ao conectar ao banco
            }
        }
        return conexao;
    }
    
    //Método para encerrada a conexão entre o java e o banco
    public static void closeConnection() {
        if(conexao != null) {
            try {
                conexao.close(); // Conexão encerrada
            } catch (SQLException e) {
                e.printStackTrace(); // Erro ao encerrar conexão
            }
        }
    }
}
