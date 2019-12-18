package com.hyp.learn.spring.demo3;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.spring.demo3
 * hyp create at 19-12-15
 **/
public class BookFactory {
    public Book create() {
        Book book = new Book("面纱", "重庆出版社");
        Author author = new Author("王钢蛋", book);
        book.setAuthor(author);
        return book;
    }

    public static Book createBook() {
        Book book = new Book("面纱", "重庆出版社");
        Author author = new Author("王钢蛋", book);
        book.setAuthor(author);
        return book;
    }
}
