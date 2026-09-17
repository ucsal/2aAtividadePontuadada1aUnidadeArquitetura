package com.ucsal.decorator;

public class NotificadorEmail implements Notificador {
    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando E-mail: " + mensagem);
    }
}
