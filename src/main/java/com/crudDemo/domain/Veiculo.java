package com.crudDemo.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "VEICULO")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    int id;

    @Column(name = "MARCA")
    String marca;

    @Column(name = "MODELO")
    String modelo;

    @Column(name = "PLACA")
    String placa;

    @ManyToOne
    @JoinColumn(name = "FK_PESSOA")
    Pessoa pessoa;

    @Column(name = "ATIVO")
    int ativo;

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
