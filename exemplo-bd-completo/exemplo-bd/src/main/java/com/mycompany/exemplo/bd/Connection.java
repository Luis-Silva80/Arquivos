/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.exemplo.bd;

import org.apache.commons.dbcp2.BasicDataSource;

/** Classe Connection
 *
 *  Utilizada para fazer a conexão com o banco de dados H2
 *  Se for usar outro SGBD, tem que trocar os dados das linhas 24 a 27
 *   
 * @author celia.taniwaki
 */
public class Connection {
    
    // Atributo
    private BasicDataSource dataSource;
    
    // Construtor
    public Connection() {
        dataSource = new BasicDataSource();
        dataSource​.setDriverClassName("org.h2.Driver");  // especifica que vai usar o driver do H2
        dataSource​.setUrl("jdbc:h2:file:./meu_banco");   // URL para conexão com o H2 (meu_banco é o nome do database)
        dataSource​.setUsername("sa");                    // nome do usuário do banco 
        dataSource​.setPassword("");                      // senha do usuário do banco                               
    }
    
    // Getter do dataSource

    public BasicDataSource getDataSource() {
        return dataSource;
    }
    
    
}
