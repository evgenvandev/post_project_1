package com.levelb.post;

/**
 * Created by Администратор on 14.02.2019.
 */
public class Main {

    public static void main(String[] args) {
        MessageBox messageBox = new MessageBox(3);
        messageBox.add(Message.MessageCategory.REGULAR, "Павлов П.И.","В посёлок Красный ключ", "Карпов К.П.");
        messageBox.add(Message.MessageCategory.REGULAR, "Павлов П.И.","В посёлок Красный ключ", "Карпов К.П.");
        messageBox.add(Message.MessageCategory.REGULAR, "Павлов П.И.","В посёлок Красный ключ", "Карпов К.П.");
        System.out.println(messageBox);
    }
}
