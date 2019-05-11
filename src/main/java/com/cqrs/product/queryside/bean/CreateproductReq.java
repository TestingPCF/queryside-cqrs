package com.cqrs.product.queryside.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * @author Brijendra and Kapil
 *
 */

@Entity
@Table(name = "Product") 
public class CreateproductReq  implements Serializable{


    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    private String skuCode = null;
    @NotNull
    private String productName = null;
    @Min(value=1)
    @NotNull
    private Integer salePrice;
    @Min(value=1)
    @NotNull
    private Integer listPrice;
    @NotNull
    private String productDescrition = null;
    @NotNull
    private String category = null;
    private boolean is_deleted = false;
    @Transient
    @JsonIgnore
    private String status = null;
    
    public CreateproductReq() {
		
	}

	public CreateproductReq(@JsonProperty String skuCode, @JsonProperty String productName, @JsonProperty Integer salePrice,
			@JsonProperty Integer listPrice, @JsonProperty String productDescrition, @JsonProperty String category,
			boolean is_deleted, String status) {
		super();
		this.skuCode = skuCode;
		this.productName = productName;
		this.salePrice = salePrice;
		this.listPrice = listPrice;
		this.productDescrition = productDescrition;
		this.category = category;
		this.is_deleted = is_deleted;
		this.status = status;
	}
    
    /**
     * @return the String
     */
    public String getSkuCode() {
        return skuCode;
    }
    /**
     * @param skuCode  to set
     */
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    /**
     * @return the String
     */
    public String getProductName() {
        return productName;
    }
    /**
     * @param productName  to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * @return the Integer
     */
    public Integer getSalePrice() {
        return salePrice;
    }
    /**
     * @param salePrice  to set
     */
    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }
    /**
     * @return the Integer
     */
    public Integer getListPrice() {
        return listPrice;
    }
    /**
     * @param listPrice  to set
     */
    public void setListPrice(Integer listPrice) {
        this.listPrice = listPrice;
    }
    /**
     * @return the String
     */
    public String getProductDescrition() {
        return productDescrition;
    }
    /**
     * @param productDescrition  to set
     */
    public void setProductDescrition(String productDescrition) {
        this.productDescrition = productDescrition;
    }
    /**
     * @return the String
     */
    public String getCategory() {
        return category;
    }
    /**
     * @param category  to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    /**
     * @return the boolean
     */
    public boolean isIs_deleted() {
        return is_deleted;
    }
    /**
     * @param is_deleted  to set
     */
    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
    /**
     * @return the String
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status  to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return the long
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

	@Override
	public String toString() {
		return "CreateproductReq [skuCode=" + skuCode + ", productName=" + productName + ", salePrice=" + salePrice
				+ ", listPrice=" + listPrice + ", productDescrition=" + productDescrition + ", category=" + category
				+ ", is_deleted=" + is_deleted + ", status=" + status + "]";
	}
}