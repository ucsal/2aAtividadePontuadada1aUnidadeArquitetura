package com.ucsal.visitor;

public interface CarrinhoVisitor {
    void visit(Livro livro);
    void visit(Eletronico eletronico);
}
