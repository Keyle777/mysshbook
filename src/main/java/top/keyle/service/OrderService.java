package top.keyle.service;

import org.apache.ibatis.annotations.Param;
import top.keyle.pojo.Order;

import java.util.List;
@SuppressWarnings("all")
public interface OrderService {
    // 新增一条订单 /order/addOrder?...
    Integer addOrder(Order order);

    // 删除一条订单 /order/deleteOrder?id=1
    Integer deleteOrder(Integer id);

    //查看一条订单详情 /order/detailOrder?id=1
    Order detailOrder(Integer id);

    //批量删除订单 /order/batchDeleteOrder?id=..
    Integer batchDeleteOrder(Integer[] orderIds);

    //查看所有订单 /order/selectOrderList
    List<Order> selectOrderList(@Param("startRow") Integer startRow,@Param("uid") Integer uid);
    //查看所有订单 /order/selectOrderList
    List<Order> selectAllOrderList(
            Integer startRow
    );
    Integer selectAllRowCount();
}
