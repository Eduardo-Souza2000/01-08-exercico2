package org.example;

public class Pedido {
    private String produtos;
    private boolean emAtendimento = true;

    public Pedido(){}
    public Pedido(String produtos) {
        this.produtos = produtos;
    }
    public Pedido(String produtos, boolean emAtendimento) {
        this.produtos = produtos;
        this.emAtendimento = emAtendimento;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }

    public boolean isEmAtendimento() {
        return emAtendimento;
    }

    public void setEmAtendimento(boolean emAtendimento) {
        this.emAtendimento = emAtendimento;
    }
}
