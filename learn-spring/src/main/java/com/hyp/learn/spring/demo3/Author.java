package com.hyp.learn.spring.demo3;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.spring.demo3
 * hyp create at 19-12-15
 **/
public class Author {
    private Book book;
    private String name;

    public Author() {
    }

    public Author(String name, Book book) {
        this.name = name;
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Author{" +
                "book=" + book.getName() +
                ", name='" + name + '\'' +
                '}';
    }
}
