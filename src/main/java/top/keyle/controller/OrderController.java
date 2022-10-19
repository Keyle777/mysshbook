package top.keyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.keyle.pojo.CartItem;
import top.keyle.pojo.Order;
import top.keyle.pojo.OrderItem;
import top.keyle.pojo.User;
import top.keyle.service.CartItemService;
import top.keyle.service.OrderItemService;
import top.keyle.service.OrderService;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@SuppressWarnings("all")
@RequestMapping("/order")
public class OrderController {
    final static Integer PAGE_SIZE = 10;

    @Autowired
    CartItemService cartItemService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    @RequestMapping("/checkOut")
    public String checkOut(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        List<CartItem> cartItemList = cartItemService.selectCartItem(currentUser.getId());
        if(cartItemList !=null){
            if(session.getAttribute("orderItemList") ==null){
                List<OrderItem> orderItemList = new ArrayList<>();
            }
            for (CartItem cartItem : cartItemList) {
                List<OrderItem> orderItemList = orderItemService.selectCurrentUserEveryOrderItem(currentUser.getId());
                session.setAttribute("orderItemList",orderItemList);
                session.setAttribute("cartItemList",cartItemService.selectCartItem(currentUser.getId()));
                String id = UUID.randomUUID().toString() + "-" + sf.format(new Date());
                Order order = new Order(id, new Timestamp(new Date().getTime()), currentUser.getId(), (Double) session.getAttribute("everySumMoney"), 1, orderItemList);
                Integer integer = orderService.addOrder(order);
                if (integer > 0){
                    session.setAttribute("order",order);
                }
                OrderItem orderItem = new OrderItem(cartItem.getBook(),cartItem.getBuyCount(),order.getId(),cartItem.getMyBook());
                Integer r = orderItemService.addOrderItem(orderItem);
                if (r > 0){
                    cartItemService.deleteCartItem(cartItem.getId());
                }
            }
            session.removeAttribute("cartItemList");
            session.setAttribute("everySumMoney",0);
            session.setAttribute("sumCartItemList",0);
        }else{
            return "/book/index";
        }
        return "cart/checkout";
    }

    @RequestMapping("/selectAllOrder")
    public String selectAllOrder(Integer page,HttpSession session){
        int pageNow = page == null ? 1 : page;
        int startRow = PAGE_SIZE * (pageNow - 1);
        session.setAttribute("orderpage",pageNow+1);
        User currentUser = (User) session.getAttribute("currentUser");
        List<Order> orderList = orderService.selectOrderList(startRow, currentUser.getId());
        Integer[] orderPages = new Integer[orderList.size()];
        int orderPageslength =orderPages.length;
        if (orderPageslength <= 0){
            orderPageslength=1;
        }
        for (int i = 0; i < (orderPageslength/10)+1; i++) {
            orderPages[i]=i+1;
        }
        session.setAttribute("orderPages",orderPages);
        session.setAttribute("orderRowsCount",orderService.selectAllRowCount());
        session.setAttribute("orderMaxPage",orderList.size()/10+1);
        session.setAttribute("orderList",orderList);
        return "order/order";
    }

    @RequestMapping("/orderDetail")
    public String orderDetail(Integer id,HttpSession session){
        List<OrderItem> orderItems = orderItemService.selectCurrentUserEveryOrderItem(id);
        System.out.println(orderItems);
        session.setAttribute("orderItemsCount",orderItems.size());
        session.setAttribute("orderItems",orderItems);
        return "order/orderdetail";
    }

    @RequestMapping("/managerAllOrder")
    public String managerAllOrder(Integer page , HttpSession session){
        int pageNow = page == null ? 1 : page;
        int startRow = PAGE_SIZE * (pageNow - 1);
        session.setAttribute("managerOrderpage",pageNow+1);
        List<Order> orderList = orderService.selectAllOrderList(startRow);
        Integer[] orderPages = new Integer[orderList.size()];
        for (int i = 0; i < (orderPages.length/10)+1; i++) {
            orderPages[i]=i+1;
        }
        session.setAttribute("managerOrderPages",orderPages);
        session.setAttribute("managerOrderRowsCount",orderService.selectAllRowCount());
        session.setAttribute("managerOrderMaxPage",orderList.size()/10+1);
        session.setAttribute("managerOrderList",orderList);

        return "manager/order_manager";
    }
}
