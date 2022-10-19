package top.keyle.pojo;

import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings("all")
/**
 * 总的订单表
 */
public class Order {
    private Integer id;
    private String orderNo;
    private Timestamp orderDate;
    private Integer orderUser;
    private Double orderMoney;
    private Integer orderStatus;

    private List<OrderItem> orderItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(Integer orderUser) {
        this.orderUser = orderUser;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", orderDate=" + orderDate +
                ", orderUser=" + orderUser +
                ", orderMoney=" + orderMoney +
                ", orderStatus=" + orderStatus +
                ", orderItems=" + orderItems +
                '}';
    }

    public Order(String orderNo, Timestamp orderDate, Integer orderUser, Double orderMoney, Integer orderStatus, List<OrderItem> orderItems) {
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.orderUser = orderUser;
        this.orderMoney = orderMoney;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
    }

    public Order(Integer id, String orderNo, Timestamp orderDate, Integer orderUser, Double orderMoney, Integer orderStatus) {
        this.id = id;
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.orderUser = orderUser;
        this.orderMoney = orderMoney;
        this.orderStatus = orderStatus;
    }

    public Order() {
    }

}
