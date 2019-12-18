package com.hyp.learn.spring.demo3;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.spring.demo3
 * hyp create at 19-12-15
 **/
public class Book {
    private String name;
    /**
     * 作者
     */
    private Author author;

    /**
     * 出版社
     */
    private String press;

    public Book() {
    }

    public Book(String name, String press) {
        this.name = name;
        this.press = press;
    }

    public Book(String name, Author author) {
        this.name = name;
        this.author = author;
    }

    public Book(String name, Author author, String press) {
        this.name = name;
        this.author = author;
        this.press = press;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author=" + author +
                ", press='" + press + '\'' +
                '}';
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }
}