package com.hyp.learn.spring.demo1;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.spring.demo1
 * hyp create at 19-12-15
 **/
public class BookServiceImpl implements BookService {
    // 方式1：之前，接口=实现类
//	private BookDao bookDao = new BookDaoImpl();
    // 方式2：接口 + setter
    private BookDao bookDao;
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    @Override
    public void addBook(){
        this.bookDao.addBook();
    }
}
