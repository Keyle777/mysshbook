package top.keyle.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.keyle.mapper.OrderMapper;
import top.keyle.pojo.Order;
import top.keyle.service.OrderService;

import java.util.List;

@SuppressWarnings("all")
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Integer addOrder(Order order) {
        return orderMapper.addOrder(order);
    }

    @Override
    public Integer deleteOrder(Integer id) {
        return orderMapper.deleteOrder(id);
    }

    @Override
    public Order detailOrder(Integer id) {
        return orderMapper.detailOrder(id);
    }

    @Override
    public Integer batchDeleteOrder(Integer[] orderIds) {
        return orderMapper.batchDeleteOrder(orderIds);
    }

    @Override
    public List<Order> selectOrderList(Integer startRow,Integer uid) {
        return orderMapper.selectOrderList(startRow,uid);
    }

    @Override
    public List<Order> selectAllOrderList(Integer startRow) {
        return orderMapper.selectAllOrderList(startRow);
    }

    @Override
    public Integer selectAllRowCount() {
        return orderMapper.selectAllRowCount();
    }
}
