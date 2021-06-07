package com.vivek.book.reader;

import com.vivek.book.reader.system.Book;
import com.vivek.book.reader.system.OnlineReader;
import com.vivek.book.reader.system.User;

public class AppTest {

    public static void main(String[] args) {
        OnlineReader onlineReader = new OnlineReader();

        Book dsBook = new Book(1, "It contains Data Structures", "Ds");
        Book algoBook = new Book(2, "It contains Algorithms", "Algo");

        onlineReader.getLibrary().addBook(dsBook);
        onlineReader.getLibrary().addBook(algoBook);

        User user1 = new User(1, " ", "Ram");
        User user2 = new User(2, " ", "Gopal");

        onlineReader.getUserManager().addUser(user1);
        onlineReader.getUserManager().addUser(user2);

        onlineReader.setActiveBook(algoBook);
        onlineReader.setActiveUser(user1);

        onlineReader.getDisplay().turnPageForward();
        onlineReader.getDisplay().turnPageForward();
        onlineReader.getDisplay().turnPageBackward();
    }

}
