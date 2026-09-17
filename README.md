# 2a Atividade Pontuada da 1a Unidade

Este projeto apresenta a implementação e explicação dos padrões de projeto de Extensão: Decorator, Iterator e Visitor.

## 1. Padrão Decorator

O padrão Decorator permite adicionar novos comportamentos a um objeto dinamicamente, colocando-os dentro de wrappers que contêm esses comportamentos.

Imaginemos um sistema onde você pode enviar notificações por E-mail. Conforme o sistema cresce, você quer permitir que o usuário receba a mesma notificação por SMS e WhatsApp, sem alterar a classe base ou criar diversas subclasses para cada combinação possível.

### Implementação:
```java
// Interface Base
public interface Notificador {
    void enviar(String mensagem);
}

// Componente Concreto
public class NotificadorEmail implements Notificador {
    public void enviar(String mensagem) {
        System.out.println("Enviando E-mail: " + mensagem);
    }
}

// Decorator Base
public abstract class NotificadorDecorator implements Notificador {
    protected Notificador wrapper;
    public NotificadorDecorator(Notificador n) { this.wrapper = n; }
    public void enviar(String msg) { wrapper.enviar(msg); }
}

// Decorators Concretos
public class NotificadorSMS extends NotificadorDecorator {
    public NotificadorSMS(Notificador n) { super(n); }
    public void enviar(String msg) {
        super.enviar(msg);
        System.out.println("Enviando SMS: " + msg);
    }
}
```

### Uso:

```java
    // NotificadorEmail é criado e os comportamentos de envio de SMS 
    // e envio de Whatsapp são anexados a ele
    Notificador notificacao = new NotificadorEmail();
    notificacao = new NotificadorSMS(notificacao);
    notificacao = new NotificadorWhatsApp(notificacao);
    
    System.out.println("Enviando notificação composta:");
    notificacao.enviar("Sua encomenda saiu para entrega!");
    System.out.println();
```

```
    Enviando notificação composta:
    Enviando E-mail: Sua encomenda saiu para entrega!
    Enviando SMS: Sua encomenda saiu para entrega!
    Enviando WhatsApp: Sua encomenda saiu para entrega!
```


## 2. Padrão Iterator

O padrão Iterator fornece uma maneira de acessar sequencialmente os elementos de uma lista sem expor sua representação subjacente.

Um exemplo bom é um carrinho de compras, que armazena diversos produtos. O Iterator permite que o sistema percorra todos os itens do carrinho para exibir um relatório ou calcular o total, independentemente de como esses produtos estão armazenados internamente (seja um ArrayList, uma LinkedList...).

### Implementação:
```java
// Interface Iterator
public interface Iterator<T> {
    boolean hasNext();
    T next();
}

// Classe Agregada
public class CarrinhoCompras implements Agregado<Produto> {
    private List<Produto> produtos = new ArrayList<>();
    
    public Iterator<Produto> criarIterator() {
        return new CarrinhoIterator();
    }

    private class CarrinhoIterator implements Iterator<Produto> {
        private int posicao = 0;
        public boolean hasNext() { return posicao < produtos.size(); }
        public Produto next() { return produtos.get(posicao++); }
    }
}
```

### Uso:

```java
    // O carrinho de compras é criado e é possível navegar por ele utilizando o iterator,
    // que funciona como um cursor para acessar a posição dessa estrutura de dados abstrata
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
```

```
    Itens no carrinho:
    - Notebook (R$ 4500.0)
    - Mouse Wireless (R$ 120.0)
    - Teclado Mecânico (R$ 350.0)
```

## 3. Padrão Visitor

O padrão Visitor permite separar um algoritmo da estrutura de objetos sobre a qual ele opera. Ele permite adicionar novas operações a estruturas de objetos complexas sem modificar as próprias classes dessas estruturas.

Em um e-commerce, temos diferentes tipos de itens (Livros, Eletrônicos). Cada tipo tem uma regra de frete diferente. Usando o Visitor, podemos criar um "Calculador de Frete" que visita cada item e aplica a regra correta, sem poluir as classes `Livro` ou `Eletronico` com lógica de logística.

### Implementação:
```java
// Interface Visitor
public interface CarrinhoVisitor {
    void visit(Livro livro);
    void visit(Eletronico eletronico);
}

// Interface do Elemento
public interface ItemCarrinho {
    void accept(CarrinhoVisitor visitor);
}

// Visitante Concreto
public class CalcularFreteVisitor implements CarrinhoVisitor {
    private double freteTotal = 0;
    public void visit(Livro l) { freteTotal += l.peso() * 2.0; }
    public void visit(Eletronico e) { freteTotal += e.preco() * 0.05; }
    public double getFreteTotal() { return freteTotal; }
}
```

### Uso:

```java
    // Os itens usam o accept para acessar a implementação do calculo de frete do visitor 
    List<ItemCarrinho> itens = new ArrayList<>();
    itens.add(new Livro("Design Patterns", 150.0, 0.8));
    itens.add(new Eletronico("Smartphone", 2500.0));
    
    CalcularFreteVisitor calculadorFrete = new CalcularFreteVisitor();
    for (ItemCarrinho item : itens) {
        item.accept(calculadorFrete);
    }

    System.out.println("Frete total calculado pelo Visitor: R$ " + calculadorFrete.getFreteTotal());

```

```
    Frete total calculado pelo Visitor: R$ 126.6
```

## Como Executar

O projeto utiliza Gradle. Para executar:

```bash
./gradlew run
```
