package top.keyle.pojo;

import java.util.List;

@SuppressWarnings("all")
public class User {
    private Integer id;
    private String uname;
    private String pwd;
    private String email;
    private Integer role;

    private List<Order> orderList;

    private CartItem cartItem;

    public User(String uname, String pwd, String email, Integer role) {
        this.uname = uname;
        this.pwd = pwd;
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", orderList=" + orderList +
                ", cartItem=" + cartItem +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    public User() {
    }

    public User(String uname, String pwd, String email, Integer role, List<Order> orderList, CartItem cartItem) {
        this.uname = uname;
        this.pwd = pwd;
        this.email = email;
        this.role = role;
        this.orderList = orderList;
        this.cartItem = cartItem;
    }
}
