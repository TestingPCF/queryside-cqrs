package com.cqrs.product.queryside.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Access;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is entity class for Order.
 *
 * @author shikhar.a || ankit-kumar
 */
@Entity
@Table(name = "ORDER")
public class Order implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	
    /**
     * Constant PRICE.
     */
    private static int PRICE = 100;

    /**
     * orderId.
     */
    @Id
    @Column(name = "order_id", unique = true, nullable = false)
    private Long orderId;

    /**
     * orderStatus.
     */
    private String orderStatus;

    /**
     * orderDate.
     */
    private Date orderDate;

    /**
     * deliveryDate.
     */
    private Date deliveryDate;

    /**
     * userEmail.
     */
    @NotNull
    private String userEmail;

    /**
     * paymentMode.
     */
    @NotNull
    private String paymentMode;

    /**
     * shippingAddress.
     */
    private String shippingAddress;

    /**
     * orderTotal.
     */
    private BigDecimal orderTotal;

    /**
     * shoppingItems.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<ShoppingItem> shoppingItems;

    /**
     * Order.
     */
    public Order() {
    }
    
    public Order(@JsonProperty Long orderId, @JsonProperty String orderStatus,  @JsonProperty Date orderDate,
    		@JsonProperty Date deliveryDate, @JsonProperty @NotNull String userEmail,
    		@JsonProperty @NotNull String paymentMode,@JsonProperty String shippingAddress,
    		@JsonProperty BigDecimal orderTotal, @JsonProperty List<ShoppingItem> shoppingItems) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.userEmail = userEmail;
        this.paymentMode = paymentMode;
        this.shippingAddress = shippingAddress;
        this.orderTotal = orderTotal;
        this.shoppingItems = shoppingItems;
    }

    /**
     * This will return a sample order object. [For testing purpose only]
     *
     * @return Order Order object
     */
    public static Order getSampleOrder() {
        Order order = new Order();
        ShoppingItem item = new ShoppingItem("Iphone_16GB",
                1,
                new BigDecimal(PRICE), new BigDecimal(PRICE),
                new BigDecimal(PRICE));
        item.setOrder(order);
        ArrayList items = new ArrayList();
        items.add(item);
        order.setOrderDate(new Date());
        order.setOrderStatus("IN_PROGRESS");
        order.setOrderTotal(new BigDecimal(PRICE));
        order.setUserEmail("shikhar.a@hcl.com");
        order.setShippingAddress("Noida, UP");
        order.setPaymentMode("CASH");
        order.setShoppingItems(new ArrayList<ShoppingItem>(items));

        return order;
    }

    /**
     * getOrderId.
     *
     * @return orderId Order Id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * setOrderId.
     *
     * @param orderIdObj Order Id
     */
    public void setOrderId(Long orderIdObj) {
        this.orderId = orderIdObj;
    }

    /**
     * getOrderStatus.
     *
     * @return orderStatus status
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * setOrderStatus.
     *
     * @param orderStatusObj status
     */
    public void setOrderStatus(String orderStatusObj) {
        this.orderStatus = orderStatusObj;
    }

    /**
     * getOrderDate.
     *
     * @return orderDate order date
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * setOrderDate.
     *
     * @param orderDateObj order date
     */
    public void setOrderDate(Date orderDateObj) {
        this.orderDate = orderDateObj;
    }

    /**
     * getDeliveryDate.
     *
     * @return deliveryDate date
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * setDeliveryDate.
     *
     * @param deliveryDateObj date
     */
    public void setDeliveryDate(Date deliveryDateObj) {
        this.deliveryDate = deliveryDateObj;
    }

    /**
     * getUserEmail.
     *
     * @return userEmail email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * setUserEmail.
     *
     * @param userEmailObj email
     */
    public void setUserEmail(String userEmailObj) {
        this.userEmail = userEmailObj;
    }

    /**
     * getPaymentMode.
     *
     * @return paymentMode payment mode
     */
    public String getPaymentMode() {
        return paymentMode;
    }

    /**
     * setPaymentMode.
     *
     * @param paymentModeObj payment mode
     */
    public void setPaymentMode(String paymentModeObj) {
        this.paymentMode = paymentModeObj;
    }

    /**
     * getShippingAddress.
     *
     * @return shippingAddress address
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * setShippingAddress.
     *
     * @param shippingAddressObj address
     */
    public void setShippingAddress(String shippingAddressObj) {
        this.shippingAddress = shippingAddressObj;
    }

    /**
     * getOrderTotal.
     *
     * @return orderTotal total
     */
    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    /**
     * setOrderTotal.
     *
     * @param orderTotalObj total
     */
    public void setOrderTotal(BigDecimal orderTotalObj) {
        this.orderTotal = orderTotalObj;
    }

    /**
     * getShoppingItems.
     *
     * @return shoppingItems items
     */
    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }

    /**
     * setShoppingItems.
     *
     * @param shoppingItemsObj items
     */
    public void setShoppingItems(
            List<ShoppingItem> shoppingItemsObj) {
        this.shoppingItems = shoppingItemsObj;
    }

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderStatus=" + orderStatus + ", orderDate=" + orderDate
				+ ", deliveryDate=" + deliveryDate + ", userEmail=" + userEmail + ", paymentMode=" + paymentMode
				+ ", shippingAddress=" + shippingAddress + ", orderTotal=" + orderTotal + ", shoppingItems="
				+ shoppingItems + "]";
	}
    
}
