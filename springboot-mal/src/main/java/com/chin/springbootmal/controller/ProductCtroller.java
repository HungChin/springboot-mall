package com.chin.springbootmal.controller;

import com.chin.springbootmal.constant.PoductCategory;
import com.chin.springbootmal.dto.ProductQueryParmeter;
import com.chin.springbootmal.dto.ProductRequest;
import com.chin.springbootmal.model.Product;
import com.chin.springbootmal.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductCtroller {

    @Autowired
    private ProductService productService;


    @GetMapping(value = "products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){

        Product product = productService.getProductById(productId);
        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(value = "/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        int productId  =  productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);
        if(product != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value = "/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId ,
                                                 @RequestBody @Valid ProductRequest productRequest){
        Product product = productService.getProductById(productId);
        if(product != null){
            productService.updateProduct(productId,productRequest);
            Product updateProduct = productService.getProductById(productId);
            return ResponseEntity.ok(updateProduct);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(value = "/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/products")
    @Validated
    public ResponseEntity<List<Product>> getAllProduct(
            @RequestParam(required = false)PoductCategory category
            ,@RequestParam(required = false) String search
            ,@RequestParam(defaultValue = "created_date") String orderByColumn
            ,@RequestParam(defaultValue = "desc") String sortMethod
            ,@RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit
            ,@RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        ProductQueryParmeter parmeter = new ProductQueryParmeter(category,search,orderByColumn,sortMethod,limit,offset);
        List<Product> productList = productService.getAllProduct(parmeter);
        return ResponseEntity.ok(productList);
    }

}
