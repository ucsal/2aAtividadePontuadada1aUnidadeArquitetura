package com.ucsal.iterator;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoCompras implements Agregado<Produto> {
    private final List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public Iterator<Produto> criarIterator() {
        return new CarrinhoIterator();
    }

    private class CarrinhoIterator implements Iterator<Produto> {
        private int posicao = 0;

        @Override
        public boolean hasNext() {
            return posicao < produtos.size();
        }

        @Override
        public Produto next() {
            if (this.hasNext()) {
                return produtos.get(posicao++);
            }
            return null;
        }
    }
}
