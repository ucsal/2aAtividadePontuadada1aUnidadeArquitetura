package com.ucsal.decorator;

public abstract class NotificadorDecorator implements Notificador {
    protected Notificador wrapper;

    public NotificadorDecorator(Notificador notificador) {
        this.wrapper = notificador;
    }

    @Override
    public void enviar(String mensagem) {
        wrapper.enviar(mensagem);
    }
}
