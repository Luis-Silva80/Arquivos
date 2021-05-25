/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.exemplo.bd;

/**
 *
 * @author celia.taniwaki
 */
public class Pokemon {
    
    // Atributos - os nomes e os tipos devem ser os mesmos das colunas da tabela do BD
    private Integer id;
    private String nome;
    private String tipo;
    
    // Método toString()
    @Override
    public String toString() {
        return "Pokemon{" + "id=" + id + ", nome=" + nome + ", tipo=" + tipo + '}';
    }

    // Setters e Getters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    

    
    
}
