package com.apitest.apicrud.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TB_tarefas")
public class Tarefa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    

    private String nome;
    private String describ;
    private Boolean feito;
  
    public long getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }

    public String getDescrib() {
        return describ;
    }

    public Boolean getFeito() {
        return feito;
    }

   public void setDescrib(String describ) {
       this.describ = describ;
   }

    public void setFeito(Boolean feito) {
        this.feito = feito;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

   

}