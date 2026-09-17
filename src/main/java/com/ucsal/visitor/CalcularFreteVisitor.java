package com.ucsal.visitor;

public class CalcularFreteVisitor implements CarrinhoVisitor {
    private double freteTotal = 0;

    @Override
    public void visit(Livro livro) {
        freteTotal += livro.peso() * 2.0;
    }

    @Override
    public void visit(Eletronico eletronico) {
        freteTotal += eletronico.preco() * 0.05;
    }

    public double getFreteTotal() {
        return freteTotal;
    }
}
