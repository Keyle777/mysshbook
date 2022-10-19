package top.keyle.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.keyle.mapper.CartItemMapper;
import top.keyle.pojo.CartItem;
import top.keyle.pojo.OrderItem;
import top.keyle.service.CartItemService;

import java.util.List;

@SuppressWarnings("all")
@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemMapper cartItemMapper;
    @Override
    public List<CartItem> selectCartItem(Integer uid) {
        return cartItemMapper.selectCartItem(uid);
    }

    @Override
    public Integer deleteCartItem(Integer id) {
        return cartItemMapper.deleteCartItem(id);
    }

    @Override
    public Integer batchDeleteCartItem(Integer[] batchCartItemIds) {
        return cartItemMapper.batchDeleteCartItem(batchCartItemIds);
    }

    @Override
    public Integer addCartItem(CartItem cartItem) {
        return cartItemMapper.addCartItem(cartItem);
    }

    @Override
    public Integer updateCartItem(Integer id, Integer num) {
        return cartItemMapper.updateCartItem(id,num);
    }
}
