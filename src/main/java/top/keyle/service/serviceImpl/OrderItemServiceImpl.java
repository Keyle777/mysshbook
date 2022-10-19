package top.keyle.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.keyle.mapper.OrderItemMapper;
import top.keyle.pojo.OrderItem;
import top.keyle.service.OrderItemService;

import java.util.List;

@Service
@SuppressWarnings("all")
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Override
    public OrderItem selectOrderItem(Integer id) {
        return orderItemMapper.selectOrderItem(id);
    }

    @Override
    public Integer deleteOrderItem(Integer id) {
        return orderItemMapper.deleteOrderItem(id);
    }

    @Override
    public Integer batchDeleteOrderItem(Integer[] batchOrderItemIds) {
        return orderItemMapper.batchDeleteOrderItem(batchOrderItemIds);
    }

    @Override
    public Integer addOrderItem(OrderItem orderItem) {
        return orderItemMapper.addOrderItem(orderItem);
    }

    @Override
    public List<OrderItem> selectCurrentUserEveryOrderItem(Integer id) {
        return orderItemMapper.selectCurrentUserEveryOrderItem(id);
    }
}
