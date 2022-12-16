package com.grupo3.digitalBooking.service.DAO;

import com.grupo3.digitalBooking.model.Product;
import com.grupo3.digitalBooking.model.ProductBookingDto;
import com.grupo3.digitalBooking.model.ProductDto;

import java.util.Collection;
import java.util.Optional;

public interface IProductService {
    void createProduct(ProductDto productSto);
    Product updateProduct (Product product, ProductDto productDto);
    Collection<Product> readProducts ();
    Optional<ProductBookingDto> readProduct (Long id);
    void deleteProduct(Long id);
}
