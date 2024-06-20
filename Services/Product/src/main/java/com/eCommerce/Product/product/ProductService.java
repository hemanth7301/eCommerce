package com.eCommerce.Product.product;

import com.eCommerce.Product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(ProductRequest request){
        var product=mapper.toProduct(request);
        return this.repository.save(product).getId();
    }

    public List<ProductResponse> findAllProducts(){
        return this.repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse findById(Integer id){
        return this.repository.findById(id).map(mapper::toProductResponse).orElseThrow(()->new EntityNotFoundException("Product not found with ID: " + id));
    }

    @Transactional
    public List<ProductPurchaseResponse>purchaseProducts(List<ProductPurchaseRequest>request){
        var productIDs=request.stream().map(ProductPurchaseRequest::productID).toList();
        var storedProducts=this.repository.findAllByIdInOrderById(productIDs);
        if(productIDs.size()!=storedProducts.size()){
            throw new ProductPurchaseException("One or more products does not exist");
        }
        var sortedRequests=request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productID)).toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for(int i=0;i<storedProducts.size();i++){
            var product=storedProducts.get(i);
            var productRequest=sortedRequests.get(i);
            var newAvailableQuantity=product.getAvailableQuantity()-productRequest.quantity();
            if(newAvailableQuantity<0){
                throw new ProductPurchaseException("Insufficient Stock for Product: "+productRequest.productID());
            }
            product.setAvailableQuantity(newAvailableQuantity);
            this.repository.save(product);
            purchasedProducts.add(mapper.toproductPurchaseResponse(product,productRequest.quantity()));
        }
        return purchasedProducts;
    }
}
