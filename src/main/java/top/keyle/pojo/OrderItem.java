package top.keyle.pojo;
@SuppressWarnings("all")
public class OrderItem {
    private Integer id;
    private Integer book;
    private Integer buyCount;
    private Integer orderBean ;

    private Book myBook;

    public OrderItem() {
    }

    public OrderItem(Integer book, Integer buyCount, Integer orderBean, Book myBook) {
        this.book = book;
        this.buyCount = buyCount;
        this.orderBean = orderBean;
        this.myBook = myBook;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(Integer orderBean) {
        this.orderBean = orderBean;
    }

    public Book getMyBook() {
        return myBook;
    }

    public void setMyBook(Book myBook) {
        this.myBook = myBook;
    }

    @Override
    public String toString() {
        return "OrderItemService{" +
                "id=" + id +
                ", book=" + book +
                ", buyCount=" + buyCount +
                ", orderBean=" + orderBean +
                ", myBook=" + myBook +
                '}';
    }
}
