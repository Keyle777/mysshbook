package top.keyle.service;

import org.apache.ibatis.annotations.Param;
import top.keyle.pojo.CartItem;
import top.keyle.pojo.OrderItem;

import java.util.List;
@SuppressWarnings("all")
public interface CartItemService {
    //获得购物车项列表 /cart/selectCartItem?uid=1
    List<CartItem> selectCartItem(Integer uid);
    //删除购物车项 /cart/deleteCartItem?id=1
    Integer deleteCartItem(Integer id);
    //批量删除购物车项 /cart/batchDeleteCartItem?id=...
    Integer batchDeleteCartItem(Integer[] batchCartItemIds);
    //加入到购物车
    Integer addCartItem(CartItem cartItem);
    Integer updateCartItem(
            @Param("id")
            Integer id,
            @Param("num")
            Integer num);
}
