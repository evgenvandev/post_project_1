package com.levelb.post;

import java.util.List;

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

        System.out.println();

        Message messageSearch = messageBox.search(2);
        messageSearch.setCategory(Message.MessageCategory.FIRST_CLASS);
        System.out.println(messageBox);

        System.out.println();

        boolean delMessage = messageBox.delete(1);
        System.out.println("Удалено письмо ? " + delMessage);
        System.out.println(messageBox);

        System.out.println();

        List<Message> list = messageBox.list();
        for (Message message : list) {
            System.out.println("MESSAGE: " + message);
        }

        System.out.println();

        List<Long> ids = messageBox.sendToMainOffice();
        System.out.println(ids);
        System.out.println(messageBox);
    }
}
