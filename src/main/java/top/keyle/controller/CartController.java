package top.keyle.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.keyle.pojo.Book;
import top.keyle.pojo.CartItem;
import top.keyle.pojo.User;
import top.keyle.service.BookService;
import top.keyle.service.CartItemService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
@SuppressWarnings("all")
public class CartController {
    @Autowired
    BookService bookService;
    @Autowired
    CartItemService cartItemService;
    @RequestMapping("/addCart")
    public String addCart(Integer id, HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        if(currentUser !=null){
            if(session.getAttribute("cartItemList") ==null){
                List<CartItem> cartItemList ;
            }
            if(id != null){
                Book book = bookService.selectBook(id);
                CartItem cartItem = new CartItem(id,1,currentUser.getId(),book);
                //虽然数据库只需要用前4个参数，但是我前端显示需要book，因此加上前端就能获取到我买的这本书的情况
                Integer integer = cartItemService.addCartItem(cartItem);
                if (integer > 0){
                    List<CartItem> cartItemList = cartItemService.selectCartItem(currentUser.getId());
                    Integer[] batchCartItemIds = new Integer[cartItemList.size()];
                    int k = 0;
                    Double everySumMoney = 0.0;
                    for (CartItem item : cartItemList) {
                        everySumMoney += item.getSumMoney();
                        batchCartItemIds[k]=item.getId();
                    }
                    session.setAttribute("batchCartItemIds",batchCartItemIds);
                    session.setAttribute("everySumMoney",everySumMoney);
                    session.setAttribute("cartItemList",cartItemList);
                    session.setAttribute("sumCartItemList",cartItemList.size());
                }
            }
            return "redirect:/book/index";
        }
        return "/user/login";
    }

    @RequestMapping("/deleteCartItem")
    public String deleteCartItem(Integer id,HttpSession session){
        Integer integer = cartItemService.deleteCartItem(id);
        User currentUser = (User) session.getAttribute("currentUser");
        if (integer > 0){
            List<CartItem> cartItemList = cartItemService.selectCartItem(currentUser.getId());
            Double everySumMoney = 0.0;
            for (CartItem item : cartItemList) {
                everySumMoney += item.getSumMoney();
            }
            session.setAttribute("cartItemList",cartItemList);
            session.setAttribute("everySumMoney",everySumMoney);
            session.setAttribute("sumCartItemList",cartItemList.size());
            return "redirect:/cart";
        }else {
            return "error";
        }
    }

    @RequestMapping("batchAllCartItem")
    public String batchAllCartItem(Integer[] batchCartItemIds,HttpSession session)
    {
        if(batchCartItemIds ==null){
            return "redirect:/user/index";
        }
        User currentUser = (User) session.getAttribute("currentUser");
            List<CartItem> cartItemList = cartItemService.selectCartItem(currentUser.getId());
        Integer rows = cartItemService.batchDeleteCartItem(batchCartItemIds);
        if(rows > 0){
            session.setAttribute("cartItemList",cartItemService.selectCartItem(currentUser.getId()));
            session.setAttribute("everySumMoney",0.0);
            session.setAttribute("sumCartItemList",0);
            return "redirect:/cart";
        }else {
            return "error";
        }
    }

    @RequestMapping("/updateCartItem")
    public String updateCartItem(Integer id ,Integer num ,HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        if (num !=null && id !=null){
            cartItemService.updateCartItem(id,num);
            session.setAttribute("cartItemList",cartItemService.selectCartItem(currentUser.getId()));
            List<CartItem> cartItemList = cartItemService.selectCartItem(currentUser.getId());
            Integer[] batchCartItemIds = new Integer[cartItemList.size()];
            int k = 0;
            Double everySumMoney = 0.0;
            for (CartItem item : cartItemList) {
                everySumMoney += item.getSumMoney();
                batchCartItemIds[k]=item.getId();
            }
            session.setAttribute("everySumMoney",everySumMoney);
            return "redirect:/cart";
        }else {
            return "error";
        }
    }
}
