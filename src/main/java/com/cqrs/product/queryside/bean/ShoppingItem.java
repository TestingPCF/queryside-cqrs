package com.cqrs.product.queryside.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.SequenceGenerator;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This is entity class for Shopping Item.
 *
 * @author shikhar.a || ankit-kumar
 */
@Entity
@Table(name = "shopping_items")
public class ShoppingItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    /**
     * shoppingItemId.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "shopping_item_sequence")
    @SequenceGenerator(name = "shopping_item_sequence",
            sequenceName = "SHOPPING_ITEM_SEQ")
    private Long shoppingItemId;

    /**
     * skuCode.
     */
    private String skuCode;

    /**
     * quantity.
     */
    private Integer quantity;

    /**
     * listPrice.
     */
    private BigDecimal listPrice;

    /**
     * salePrice.
     */
    private BigDecimal salePrice;

    /**
     * totalPrice.
     */
    private BigDecimal totalPrice;

    /**
     * order.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order")
    @JsonIgnore
    private Order order;

    /**
     * ShoppingItem.
     */
    public ShoppingItem() {
    }

    /**
     * ShoppingItem.
     *
     * @param skuCodeObj    Sku Code
     * @param quantityObj   Quantity
     * @param listPriceObj  List Price
     * @param salePriceObj  Sale Price
     * @param totalPriceObj Total Price
     */
    public ShoppingItem(String skuCodeObj,
                        Integer quantityObj,
                        BigDecimal listPriceObj,
                        BigDecimal salePriceObj,
                        BigDecimal totalPriceObj) {
        this.skuCode = skuCodeObj;
        this.quantity = quantityObj;
        this.listPrice = listPriceObj;
        this.salePrice = salePriceObj;
        this.totalPrice = totalPriceObj;
    }

    /**
     * ShoppingItem.
     *
     * @param shoppingItemIdObj Item Id
     * @param skuCodeObj        Sku Code
     * @param quantityObj       Quantity
     * @param listPriceObj      List Price
     * @param salePriceObj      Sale Price
     * @param totalPriceObj     Total Price
     * @param orderObj          Order
     */
    public ShoppingItem(Long shoppingItemIdObj,
                        String skuCodeObj,
                        Integer quantityObj,
                        BigDecimal listPriceObj,
                        BigDecimal salePriceObj,
                        BigDecimal totalPriceObj,
                        Order orderObj) {
        this.shoppingItemId = shoppingItemIdObj;
        this.skuCode = skuCodeObj;
        this.quantity = quantityObj;
        this.listPrice = listPriceObj;
        this.salePrice = salePriceObj;
        this.totalPrice = totalPriceObj;
        this.order = orderObj;
    }

    /**
     * getSkuCode.
     *
     * @return skuCode Code
     */
    public String getSkuCode() {
        return skuCode;
    }

    /**
     * setSkuCode.
     *
     * @param skuCodeObj Code
     */
    public void setSkuCode(String skuCodeObj) {
        this.skuCode = skuCodeObj;
    }

    /**
     * getQuantity.
     *
     * @return quantity Quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * setQuantity.
     *
     * @param quantityObj Quantity
     */
    public void setQuantity(Integer quantityObj) {
        this.quantity = quantityObj;
    }

    /**
     * getListPrice.
     *
     * @return listPrice List Price
     */
    public BigDecimal getListPrice() {
        return listPrice;
    }

    /**
     * setListPrice.
     *
     * @param listPriceObj List Price
     */
    public void setListPrice(BigDecimal listPriceObj) {
        this.listPrice = listPriceObj;
    }

    /**
     * getSalePrice.
     *
     * @return salePrice Sale Price
     */
    public BigDecimal getSalePrice() {
        return salePrice;
    }

    /**
     * setSalePrice.
     *
     * @param salePriceObj Sale Price
     */
    public void setSalePrice(BigDecimal salePriceObj) {
        this.salePrice = salePriceObj;
    }

    /**
     * getTotalPrice.
     *
     * @return totalPrice Total Price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * setTotalPrice.
     *
     * @param totalPriceObj Total Price
     */
    public void setTotalPrice(BigDecimal totalPriceObj) {
        this.totalPrice = totalPriceObj;
    }

    /**
     * getShoppingItemId.
     *
     * @return shoppingItemId Item Id
     */
    public Long getShoppingItemId() {
        return shoppingItemId;
    }

    /**
     * setShoppingItemId.
     *
     * @param shoppingItemIdObj Item Id
     */
    public void setShoppingItemId(Long shoppingItemIdObj) {
        this.shoppingItemId = shoppingItemIdObj;
    }

    /**
     * getOrder.
     *
     * @return order Order object
     */
    public Order getOrder() {
        return order;
    }

    /**
     * setOrder.
     *
     * @param orderObj Order object
     */
    public void setOrder(Order orderObj) {
        this.order = orderObj;
    }

	@Override
	public String toString() {
		return "ShoppingItem [shoppingItemId=" + shoppingItemId + ", skuCode=" + skuCode + ", quantity=" + quantity
				+ ", listPrice=" + listPrice + ", salePrice=" + salePrice + ", totalPrice=" + totalPrice + ", order="
				+ order + "]";
	}
}
