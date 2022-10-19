package top.keyle.service;

import top.keyle.pojo.OrderItem;

import java.util.List;

@SuppressWarnings("all")
public interface OrderItemService {
    //查询订单信息 /orderdetail/selectOrderItem?id=1
    OrderItem selectOrderItem(Integer id);
    //删除订单 /orderdetail/deleteOrderItem?id=1
    Integer deleteOrderItem(Integer id);
    //批量删除订单 /orderdetail/batchDeleteOrderItem?id=1...
    Integer batchDeleteOrderItem(Integer[] batchOrderItemIds);
    //增加一个订单详情项
    Integer addOrderItem(OrderItem orderItem);
    //查询某个人的订单
    List<OrderItem> selectCurrentUserEveryOrderItem(Integer id);
}
