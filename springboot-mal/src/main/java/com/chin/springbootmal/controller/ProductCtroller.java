package com.chin.springbootmal.controller;

import com.chin.springbootmal.constant.ProductCategory;
import com.chin.springbootmal.dto.product.ProductQueryParmeter;
import com.chin.springbootmal.dto.product.ProductRequest;
import com.chin.springbootmal.model.Product;
import com.chin.springbootmal.service.ProductService;
import com.chin.springbootmal.util.Page;
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

    /**
     * 取得產品資料
     * @param productId 產品Id Integer
     * @return ResponseEntity<Product>
     */
    @GetMapping(value = "/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){

        Product product = productService.getProductById(productId);
        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 新增產品資料
     * @param productRequest 新增產品資訊 ProductRequest
     * @return ResponseEntity<Product>
     */
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

    /**
     * 更新產品資料
     * @param productId 產品Id Integer
     * @param productRequest 更新產品資訊 ProductRequest
     * @return ResponseEntity<Product>
     */
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

    /**
     * 刪除產品資料
     * @param productId 產品Id Integer
     * @return ResponseEntity<?>
     */
    @DeleteMapping(value = "/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    /**
//     * @param category 產品類別 ProductCategory
//     * @param search 關鍵字查詢 String
//     * @param orderByColumn 排序欄位名稱 String
//     * @param sortMethod 排序方式 String
//     * @param limit 限制資料筆數 Integer
//     * @param offset 設定資料從第幾筆開始 Integer
//     * @return ResponseEntity<List<Product>>
//     */
//    @GetMapping("/products")
//    @Validated
//    public ResponseEntity<List<Product>> getAllProduct(
//            @RequestParam(required = false)PoductCategory category
//            ,@RequestParam(required = false) String search
//            ,@RequestParam(defaultValue = "created_date") String orderByColumn
//            ,@RequestParam(defaultValue = "desc") String sortMethod
//            ,@RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit
//            ,@RequestParam(defaultValue = "0") @Min(0) Integer offset
//    ){
//        ProductQueryParmeter parmeter = new ProductQueryParmeter(category,search,orderByColumn,sortMethod,limit,offset);
//        List<Product> productList = productService.getAllProduct(parmeter);
//        return ResponseEntity.ok(productList);
//    }

    /**
     * 查詢全部產品資料(依挑檔條件查詢及排序及設定分頁)
     * @param category 產品類別 ProductCategory
     * @param search 關鍵字查詢 String
     * @param orderByColumn 排序欄位名稱 String
     * @param sortMethod 排序方式 String
     * @param limit 限制資料筆數 Integer
     * @param offset 設定資料從第幾筆開始 Integer
     * @return ResponseEntity<Page<Product>> Page<>Product data
     */
    @GetMapping("/products")
    @Validated
    public ResponseEntity<Page<Product>> getAllProduct(
            @RequestParam(required = false) ProductCategory category
            ,@RequestParam(required = false) String search
            ,@RequestParam(defaultValue = "created_date") String orderByColumn
            ,@RequestParam(defaultValue = "desc") String sortMethod
            ,@RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit
            ,@RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        ProductQueryParmeter parmeter = new ProductQueryParmeter(category,search,orderByColumn,sortMethod,limit,offset);
        //查詢挑檔後產品資料
        List<Product> productList = productService.getAllProduct(parmeter);
        //查詢總筆數
        Integer total = productService.getProductCount(parmeter);
        Page<Product> pageData = new Page<>();
        pageData.setLimit(limit);
        pageData.setOffset(offset);
        pageData.setTotal(total);
        pageData.setResults(productList);
        return ResponseEntity.ok(pageData);
    }

}
