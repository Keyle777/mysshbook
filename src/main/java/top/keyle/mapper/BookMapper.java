package top.keyle.mapper;

import org.apache.ibatis.annotations.Param;
import top.keyle.pojo.Book;

import java.util.List;

@SuppressWarnings("all")
public interface BookMapper {
    //查询某一本书 /index/selectBook?id=1
    Book selectBook(Integer id);
    //查询所有书，可选作者查询/index/selectBookList?author=李四&page=0
    List<Book> selectBookList(
            @Param("author")
            String author,
            @Param("minPrice")
            Double minPrice,
            @Param("maxPrice")
            Double maxPrice,
            @Param("startRow")
            Integer startRow
    );
    //增加一本书 /manager/addBook
    Integer addBook(Book book);
    //删除一本书 /manager/deleteBook?id=1
    Integer deleteBook(Integer id);
    //修改某本书 /manager/updateBook?id=1&...
    Integer updateBook(Book book);
    //批量删除 /manager/batchDeleteBook?...
    Integer batchDeleteBook(Integer[] bookIds);

    //书的总数
    Integer selectBookCount();
}
