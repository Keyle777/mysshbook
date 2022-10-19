package top.keyle.pojo;

import java.math.BigDecimal;

@SuppressWarnings("all")
public class CartItem {
    private Integer id;
    private Integer book;
    private Integer buyCount;
    private Integer userBean;
    private Book myBook;
    private Double sumMoney;

    public CartItem() {
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", book=" + book +
                ", buyCount=" + buyCount +
                ", userBean=" + userBean +
                ", myBook=" + myBook +
                ", sumMoney=" + sumMoney +
                '}';
    }

    public CartItem(Integer book, Integer buyCount, Integer userBean) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
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

    public Integer getUserBean() {
        return userBean;
    }

    public void setUserBean(Integer userBean) {
        this.userBean = userBean;
    }

    public Book getMyBook() {
        return myBook;
    }

    public void setMyBook(Book myBook) {
        this.myBook = myBook;
    }

    public void setSumMoney(Double sumMoney) {
        BigDecimal bigDecimalPrice = new BigDecimal(""+getMyBook().getPrice());
        BigDecimal bigDecimalBuyCount = new BigDecimal(""+buyCount);
        BigDecimal bigDecimalXJ = bigDecimalPrice.multiply(bigDecimalBuyCount);
        sumMoney = bigDecimalXJ.doubleValue() ;
        this.sumMoney = sumMoney;
    }
    public Double getSumMoney() {
        BigDecimal bigDecimalPrice = new BigDecimal(""+getMyBook().getPrice());
        BigDecimal bigDecimalBuyCount = new BigDecimal(""+buyCount);
        BigDecimal bigDecimalXJ = bigDecimalPrice.multiply(bigDecimalBuyCount);
        sumMoney = bigDecimalXJ.doubleValue() ;
        return sumMoney;
    }

    public CartItem(Integer book, Integer buyCount, Integer userBean, Book myBook) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
        this.myBook = myBook;
    }
}
