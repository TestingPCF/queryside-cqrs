package com.cqrs.product.queryside.response;

import java.util.ArrayList;
import java.util.List;

import com.cqrs.product.queryside.bean.CreateproductReq;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * @author Brijendra and Kapil
 * Entity class for View Product Response
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViewproductRes {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<CreateproductReq> productList = new ArrayList<CreateproductReq>();
    private String skuCode = null;
    private String statusCode=null;
    private String status = null;
    /**
     * @return the List<CreateproductReq>
     */
    public List<CreateproductReq> getProductList() {
        return productList;
    }
    /**
     * @param productList  to set
     */
    public void setProductList(List<CreateproductReq> productList) {
        this.productList = productList;
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
    public String getStatusCode() {
        return statusCode;
    }
    /**
     * @param statusCode  to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
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
}
