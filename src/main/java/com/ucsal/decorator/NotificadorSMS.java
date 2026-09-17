package com.ucsal.decorator;

public class NotificadorSMS extends NotificadorDecorator {
    public NotificadorSMS(Notificador notificador) {
        super(notificador);
    }

    @Override
    public void enviar(String mensagem) {
        super.enviar(mensagem);
        System.out.println("Enviando SMS: " + mensagem);
    }
}
