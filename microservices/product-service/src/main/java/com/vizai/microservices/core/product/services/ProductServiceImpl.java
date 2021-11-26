package com.vizai.microservices.core.product.services;

import com.vizai.api.core.product.Product;
import com.vizai.api.core.product.ProductService;
import com.vizai.api.exceptions.InvalidInputException;
import com.vizai.api.exceptions.NotFoundException;
import com.vizai.util.http.ServiceUtil;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ServiceUtil serviceUtil;

    @Autowired
    public ProductServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Product getProduct(int productId) {
        if (productId < 1)
            throw new InvalidInputException("Invalid productId: " + productId);
        if (productId == 13)
            throw new NotFoundException("No product found for productId: " + productId);
        return new Product(productId, "name-"+productId, 123, serviceUtil.getServiceAddress());
    }
}
