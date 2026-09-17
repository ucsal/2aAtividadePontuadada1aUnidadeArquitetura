package com.ucsal.iterator;

public record Produto(String nome, double preco) {
    @Override
    public String toString() {
        return nome + " (R$ " + preco + ")";
    }
}
