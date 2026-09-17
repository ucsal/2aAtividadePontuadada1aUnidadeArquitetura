package com.ucsal.decorator;

public class NotificadorWhatsApp extends NotificadorDecorator {
    public NotificadorWhatsApp(Notificador notificador) {
        super(notificador);
    }

    @Override
    public void enviar(String mensagem) {
        super.enviar(mensagem);
        System.out.println("Enviando WhatsApp: " + mensagem);
    }
}
