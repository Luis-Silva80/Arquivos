/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.exemplo.bd;

import java.util.List;
import java.util.Scanner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author celia.taniwaki
 */
public class TesteBanco {
    
    public static void main(String[] args) {
        
        // Cria um objeto da classe Connection
        // Esse objeto será usado para fazer a conexão com o SGBD
        Connection config = new Connection();
        
        /* Instancia um objeto da classe JdbcTemplate, que será usado para passar
           os comandos para o banco de dados.
           Ao instanciar esta classe, passamos como argumento para o construtor o 
           config.getDataSource()
        */
        JdbcTemplate con = new JdbcTemplate(config.getDataSource());
        
        // O comando SQL deve ser passado como uma String, dentro de aspas duplas
        // esse comando foi apenas um teste    
        //con.queryForList("select * from aluno;");
        
        
        /* Uso simples do JDBC (sem mapeamento de classe)
           O resultado do select é atribuído a uma lista
        */
        List pokemonUsoSimples = con.queryForList("select * from Pokemon;");
        System.out.println("Exibindo o resultado do select da tabela Pokemon - uso simples:");
        System.out.println(pokemonUsoSimples);
        
        /* Uso avançado do JDBC - inclui o uso de uma classe para mapear a tabela
           Para isso, criamos a classe Pokemon, cujos atributos devem corresponder
           às colunas da tabela Pokemon (tanto o nome das colunas como os tipos).
           
           O objeto BeanPropertyRowMapper é responsável por mapear cada linha da tabela
           a um objeto da classe Pokemon
        */
        List<Pokemon> pokemonUsoAvancado = con.query("select * from pokemon;",
                                           new BeanPropertyRowMapper(Pokemon.class));

        // Exibindo a lista pokemonsUsoAvancado (resultado da query anterior)
        System.out.println("\nExibindo o resultado do select da tabela Pokemon - uso avançado:");
        for (Pokemon p : pokemonUsoAvancado) {
            System.out.println(p);
        }
        
        // Para efetuar insert, delete, update na tabela, utilize o método con.update() 
        
        // Inserindo dados na tabela Pokemon
        // É possível colocar a instrução do insert como argumento para o con.update:
        con.update("insert into pokemon values (null,'Bulbasaur','planta');");

        pokemonUsoAvancado = con.query("select * from pokemon;",
                                       new BeanPropertyRowMapper(Pokemon.class));

        // Exibindo a lista pokemonsUsoAvancado (resultado da query anterior)
        System.out.println("\nExibindo o resultado do select após inserir o Bulbasaur");
        for (Pokemon p : pokemonUsoAvancado) {
            System.out.println(p);
        }
        
        // Existe uma outra forma de se fazer insert, que fica mais genérico
        String insertStatement = "insert into pokemon values (null,?,?);";
        // Na hora em que chamo o con.update, posso passar valores para substituir os
        // pontos de interrogação
        con.update(insertStatement, "Magikarp", "água");
        
        String nomePokemon = "Gyarados";
        String tipoPokemon = "água";
        // Posso passar variáveis tb para que seus valores substituam os pontos de interrogação
        con.update(insertStatement, nomePokemon, tipoPokemon);
        
        // Posso tb pedir que o usuário digite esses valores
        Scanner leitor = new Scanner(System.in);
        System.out.println("Digite o nome e o tipo do Pokemon separados por espaço:");
        nomePokemon = leitor.next();
        tipoPokemon = leitor.next();
        con.update(insertStatement, nomePokemon, tipoPokemon);
        
        pokemonUsoAvancado = con.query("select * from pokemon;",
                                       new BeanPropertyRowMapper(Pokemon.class));

        // Exibindo a lista pokemonsUsoAvancado (resultado da query anterior)
        System.out.println("\nExibindo o resultado do select após mais inserções:");
        for (Pokemon p : pokemonUsoAvancado) {
            System.out.println(p);
        }
        
        // Select apenas dos pokemons do tipo fogo
        List<Pokemon> pokemonTipoFogo = con.query("select * from pokemon where tipo = ?;",
                                          new BeanPropertyRowMapper(Pokemon.class),"fogo");

        // Exibindo a lista pokemonsTipoFogo (resultado da query anterior)
        System.out.println("\nExibindo o resultado do select apenas do tipo fogo:");
        for (Pokemon p : pokemonTipoFogo) {
            System.out.println(p);
        }
        
        // Select apenas dos pokemons começados com a letra B
        List<Pokemon> pokemonComecadoComB = con.query("select * from pokemon where nome like ?;",
                                          new BeanPropertyRowMapper(Pokemon.class),"B%");

        // Exibindo a lista pokemonComecadoComB (resultado da query anterior)
        System.out.println("\nExibindo o resultado do select pokemon iniciado com a letra B:");
        for (Pokemon p : pokemonComecadoComB) {
            System.out.println(p);
        }
        
//        // Excluir os Bulbasaurs repetidos
//        con.update("delete from pokemon where id in (7, 11, 15)");
//        pokemonUsoAvancado = con.query("select * from pokemon;",
//                                       new BeanPropertyRowMapper(Pokemon.class));
//
//        // Exibindo a lista pokemonsUsoAvancado (resultado da query anterior)
//        System.out.println("\nExibindo o resultado do select após excluir os Bulbasaurs repetidos:");
//        for (Pokemon p : pokemonUsoAvancado) {
//            System.out.println(p);
//        }
        
        // Update para mudar o tipo do Hitmonlee de normal para lutador
        con.update("update pokemon set tipo = ? where nome = ?;", "lutador", "Hitmonlee");
        // Update para mudar o nome do Blastoide para Squirtle
        con.update("update pokemon set nome = ? where nome = ?;", "Squirtle", "Blastoise");
        pokemonUsoAvancado = con.query("select * from pokemon;",
                                       new BeanPropertyRowMapper(Pokemon.class));

        // Exibindo a lista pokemonsUsoAvancado (resultado da query anterior)
        System.out.println("\nExibindo o resultado do select após updates:");
        for (Pokemon p : pokemonUsoAvancado) {
            System.out.println(p);
        }
        
        
         
    }
    
}
