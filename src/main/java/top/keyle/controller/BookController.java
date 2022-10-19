package top.keyle.controller;

import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import top.keyle.pojo.Book;
import top.keyle.service.BookService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/book")
@Controller
@SuppressWarnings("all")
public class BookController {
    final static Integer PAGE_SIZE = 10;
    @Autowired
    private BookService bookService;

    @RequestMapping("/index")
    public String index(String author, Double minPrice, Double maxPrice, Integer page, HttpSession session) {
        int pageNow = page == null ? 1 : page;
        int startRow = PAGE_SIZE * (pageNow - 1);
        session.setAttribute("page",pageNow+1);
        Integer bookCount = bookService.selectBookCount();
        int s =(bookCount / PAGE_SIZE) + 1;
        session.setAttribute("maxPage",s);
        Integer[] pageCount = new Integer[s];
        for (int i = 0; i <s ; i++) {
            pageCount[i] = i+1;
        }
        session.setAttribute("pageCount",pageCount);
        session.setAttribute("sumPage",bookService.selectBookCount());
        List<Book> bookList = bookService.selectBookList(author, minPrice, maxPrice, startRow);
        if (bookList == null){
            session.setAttribute("bookList", "未找到任何相关图书");
        }else{
            session.setAttribute("bookList", bookList);
        }
        return "index";
    }

    @RequestMapping("/addBook")
    public String addBook(String bookName,Double price , String author ,Integer saleCount ,Integer bookCount,HttpSession session){
        Book book = new Book("huozhe.jpg",bookName,price,author,saleCount,bookCount);
        bookService.addBook(book);
        List<Book> managerBookList = bookService.selectBookList(null, null, null, 0);
        session.setAttribute("managerBookList",managerBookList);
        return "manager/book_manager";
    }

    @RequestMapping("/updateBook")
    public String updateBook(Integer id,String bookName,Double price , String author ,Integer saleCount ,Integer bookCount,HttpSession session){
        Book book = new Book(id,bookName,price,author,saleCount,bookCount);
        bookService.updateBook(book);
        List<Book> managerBookList = bookService.selectBookList(null, null, null, 0);
        session.setAttribute("managerBookList",managerBookList);
        return "manager/book_manager";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(Integer id,HttpSession session)
    {
        bookService.deleteBook(id);
        List<Book> managerBookList = bookService.selectBookList(null, null, null, 0);
        session.setAttribute("managerBookList",managerBookList);
        return "redirect:/book/bookmanager";
    }

    @RequestMapping("/bookmanager")
    public String bookmanager(Integer page ,HttpSession session)
    {
        int pageNow = page == null ? 1 : page;
        int startRow = PAGE_SIZE * (pageNow - 1);
        session.setAttribute("bookManagerPage",pageNow+1);
        Integer bookCount = bookService.selectBookCount();
        if(bookCount <=0){
            bookCount=1;
        }
        int s =(bookCount / PAGE_SIZE) + 1;
        session.setAttribute("bookManagerMaxPage",s);
        Integer[] pageCount = new Integer[s];
        for (int i = 0; i <s ; i++) {
            pageCount[i] = i+1;
        }
        session.setAttribute("bookManagerPageCount",pageCount);
        session.setAttribute("bookManagerSumPage",bookService.selectBookCount());
        List<Book> managerBookList = bookService.selectBookList(null, null, null, startRow);
        session.setAttribute("managerBookList",managerBookList);
        return "manager/book_manager";
    }

    @RequestMapping("/bookedit")
    public String bookedit(Integer id ,HttpSession session){
        session.setAttribute("bookid",id);
        return "manager/book_edit";
    }
    @RequestMapping("/fileUp")
    public String testUp(MultipartFile photo, HttpSession session) throws IOException {
        //获取上传的文件的文件名
        String fileName = photo.getOriginalFilename();
        //处理文件重名问题
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + hzName;
        //获取服务器中photo目录的路径
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("img");
        File file = new File(photoPath);
        if(!file.exists()){
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + fileName;
        //实现上传功能
        photo.transferTo(new File(finalPath));
        return "bookedit";
    }
}
