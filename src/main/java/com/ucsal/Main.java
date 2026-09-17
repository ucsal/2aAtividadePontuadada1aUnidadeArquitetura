package com.ucsal;

import com.ucsal.decorator.*;
import com.ucsal.iterator.*;
import com.ucsal.visitor.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Padrão Decorator - Sistema de Notificações ===");
        Notificador notificacao = new NotificadorEmail();
        notificacao = new NotificadorSMS(notificacao);
        notificacao = new NotificadorWhatsApp(notificacao);
        
        System.out.println("Enviando notificação composta:");
        notificacao.enviar("Sua encomenda saiu para entrega!");
        System.out.println();

        System.out.println("=== Padrão Iterator - Carrinho de Compras ===");
        CarrinhoCompras carrinho = new CarrinhoCompras();
        carrinho.adicionarProduto(new Produto("Notebook", 4500.0));
        carrinho.adicionarProduto(new Produto("Mouse Wireless", 120.0));
        carrinho.adicionarProduto(new Produto("Teclado Mecânico", 350.0));

        Iterator<Produto> iterator = carrinho.criarIterator();
        System.out.println("Itens no carrinho:");
        while (iterator.hasNext()) {
            System.out.println("- " + iterator.next());
        }
        System.out.println();

        System.out.println("=== Demonstrando Padrão Visitor (Cálculo de Frete) ===");
        List<ItemCarrinho> itens = new ArrayList<>();
        itens.add(new Livro("Design Patterns", 150.0, 0.8));
        itens.add(new Eletronico("Smartphone", 2500.0));

        CalcularFreteVisitor calculadorFrete = new CalcularFreteVisitor();
        for (ItemCarrinho item : itens) {
            item.accept(calculadorFrete);
        }

        System.out.println("Frete total calculado pelo Visitor: R$ " + calculadorFrete.getFreteTotal());
    }
}
