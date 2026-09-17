package com.ucsal.visitor;

public record Eletronico(String nome, double preco) implements ItemCarrinho {
    @Override
    public void accept(CarrinhoVisitor visitor) {
        visitor.visit(this);
    }
}
