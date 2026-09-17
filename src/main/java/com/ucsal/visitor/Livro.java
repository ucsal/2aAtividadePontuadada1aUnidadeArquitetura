package com.ucsal.visitor;

public record Livro(String nome, double preco, double peso) implements ItemCarrinho {
    @Override
    public void accept(CarrinhoVisitor visitor) {
        visitor.visit(this);
    }
}
