package com.cqrs.product.queryside.cache;

import com.cqrs.product.queryside.bean.CreateproductReq;

/**
 * This is an interface have methods to work with redis cache.
 * @author shikhar.a || MohitKri
 */
public interface ProductCacheManager {
    /**
     * Method to put product in cache.
     * @param product product
     */
    void cacheProductDetails(CreateproductReq product);

    /**
     * Method to remove product in cache.
     * @param product product
     */
    void removeProductFromCache(CreateproductReq product);

    /**
     * Method to get product from cache.
     * @param skuCode skucode
     * @return product object
     */
    CreateproductReq getProductFromCache(String skuCode);
}
