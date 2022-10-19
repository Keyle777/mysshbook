package top.keyle.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.keyle.mapper.BookMapper;
import top.keyle.pojo.Book;
import top.keyle.service.BookService;

import java.util.List;

@Service
@SuppressWarnings("all")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public Book selectBook(Integer id) {
        return bookMapper.selectBook(id);
    }

    @Override
    public List<Book> selectBookList(String author, Double minPrice, Double maxPrice, Integer startRow) {
        return bookMapper.selectBookList(author,minPrice,maxPrice,startRow);
    }

    @Override
    public Integer addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public Integer deleteBook(Integer id) {
        return bookMapper.deleteBook(id);
    }

    @Override
    public Integer updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public Integer batchDeleteBook(Integer[] bookIds) {
        return bookMapper.batchDeleteBook(bookIds);
    }

    @Override
    public Integer selectBookCount() {
        return bookMapper.selectBookCount();
    }
}
