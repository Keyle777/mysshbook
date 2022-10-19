import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.keyle.mapper.OrderMapper;
import top.keyle.pojo.*;
import top.keyle.service.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_mapper.xml", "classpath:applicationContext_service.xml"})
public class MyTest {
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    OrderMapper orderMapper;
    MessageDigest md5;
    @Test
    public void testA() throws NoSuchAlgorithmException {
        User user = new User("admin", "123456", "101@qq.com", 1,null,null);
        String pwd = user.getPwd();
            md5 = MessageDigest.getInstance("MD5");
            md5.update(pwd.getBytes());
            pwd = new BigInteger(1, md5.digest()).toString(16);
            user.setPwd(pwd);
        Integer row = userService.insertUser(user);
        if (row > 0){
            System.out.println("添加成功!");
        }else{
            System.out.println("Error!");
        }
    }
    @Test
    public void detailsuser(){
        System.out.println("----------------------------------------------------------------");
        System.out.println(userService.detailUser(1));
    }
    @Test
    public void update(){
        User user = new User();
        user.setUname("keyle");
        user.setId(1);
        userService.updateUser(user);
    }
    @Test
    public void selectBookList(){
        for (Book book : bookService.selectBookList(null, 1.0, 35.0, 0)) {
            System.out.println(book);
        }
    }
    @Test
    public void addBook(){
        Book book = new Book("a.jpg", "三毛流浪记", 25.8, "张爱玲", 0, 100);
        bookService.addBook(book);
        System.out.println(book.getId());
    }
    @Test
    public void updateBook(){
        Book book = new Book("b.jpg", "流浪记", 12.0, "张爱玲", 0, 100);
        book.setId(37);
        bookService.updateBook(book);
    }
    @Test
    public void batchDeleteBook(){
        Integer[] row = {36,37};
        bookService.batchDeleteBook(row);
    }
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    @Test
    public void addOrder(){
        String id = UUID.randomUUID().toString()+"-"+sf.format(new Date());
        orderService.addOrder(new Order(id,new Timestamp(new Date().getTime()),1,50.0,1,null));
    }

    @Test
    public void deleteOrder(){
        orderService.deleteOrder(2);
    }
    @Test
    public void detailOrder(){
        System.out.println(orderService.detailOrder(3));
    }

    @Test
    public void batchDeleteOrder(){
        orderService.batchDeleteOrder(new Integer[]{1,3});
    }
    @Test
    public void selectorderitem(){
        System.out.println(orderItemService.selectOrderItem(1));
    }
    @Test
    public void deleteOrderItem(){
        orderItemService.batchDeleteOrderItem(new Integer[]{1,2});
    }

    @Test
    public void addCartItem(){
        Book book = bookService.selectBook(1);
        System.out.println(cartItemService.addCartItem(new CartItem(1, 20, 1)));
    }
    @Test
    public void selectCartItem(){
        for (CartItem cartItem : cartItemService.selectCartItem(1)) {
            System.out.println(cartItem);
        }
    }
    @Test
    public void batchDeleteCartItem(){
        System.out.println(cartItemService.selectCartItem(1));
    }

    @Test
    public void s1(){
        List<OrderItem> orderItems = orderItemService.selectCurrentUserEveryOrderItem(3);
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }

    @Test
    public void addOrderItem(){
        OrderItem orderItem = new OrderItem(1,21,3,null);
        orderItemService.addOrderItem(orderItem);
    }

    @Test
    public  void buyCount(){
        cartItemService.updateCartItem(30,5);
    }
    @Test
    public void  selectOrderList(){
        for (Order order : orderService.selectOrderList(0, 1)) {
            System.out.println(order);
        }
    }
    @Test
    public void detailOrder1()
    {
        List<OrderItem> orderItems = orderItemService.selectCurrentUserEveryOrderItem(23);
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }

}
