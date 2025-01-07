package com.example.inventory.inventory.productsInInventory;

import com.example.inventory.inventory.categories.Categories;
import com.example.inventory.inventory.categories.CategoriesRepo;
import com.example.inventory.inventory.products.Products;
import com.example.inventory.inventory.products.dto.ProductDto;
import com.example.inventory.inventory.productsInInventory.InventoryProductRepo;
import com.example.inventory.inventory.productsInInventory.InventoryProducts;
import com.example.inventory.inventory.soldProducts.SoldProducts;
import com.example.inventory.inventory.soldProducts.SoldProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryProductsService {
    @Autowired
    private InventoryProductRepo inventoryProductRepo;
    @Autowired
    CategoriesRepo categoriesRepo;

    @Autowired private SoldProductsRepo soldProductsRepo;
    public HashMap<Object, Object> addProduct(ProductDto dto) {
        HashMap<Object, Object> response = new HashMap<>();
        List<Categories> categories = categoriesRepo.findAllById(dto.getCategoriesId());
        InventoryProducts product = new InventoryProducts(dto);
        product.setCategories(categories);
        for (Categories category : categories) {
            category.getInventoryProducts().add(product);
        }
        InventoryProducts savedProduct = inventoryProductRepo.save(product);
        if (savedProduct != null) {
            response.put("data", savedProduct);
        } else {
            response.put("error", "Cannot save product");
        }

        return response;
    }


public HashMap<Object, Object> updateProduct(ProductDto dto){
    HashMap<Object,Object> msg = new HashMap<Object,Object>();
    Optional<InventoryProducts> product= inventoryProductRepo.findById(dto.getId());

    if(product.isPresent()) {
        if (dto.getName() != null)
            product.get().setName(dto.getName());
        if (dto.getPrice() != null)
            product.get().setPrice(dto.getPrice());
//        if(dto.getDiscount() != null)
//            product.get().setDiscount(dto.getDiscount());
        if(dto.getAmount() != null)
            product.get().setAmount(dto.getAmount());

        if(dto.getDescription() != null)
            product.get().setDescription(dto.getDescription());
        if(dto.getCode() != null)
            product.get().setCode(dto.getCode());

        InventoryProducts updatedProduct= inventoryProductRepo.save(product.get());
msg.put("data", updatedProduct);
    }
    else  msg.put("error","can not find product");
    return msg;

}

    public HashMap<Object, Object> deleteProduct(Long id) {
        HashMap<Object, Object> msg = new HashMap<>();
        Optional<InventoryProducts> productOptional = inventoryProductRepo.findById(id);

        if (productOptional.isPresent()) {
            InventoryProducts product = productOptional.get();

            // Remove associations in the `inventory_product_category` table
            for (Categories category : product.getCategories()) {
                category.getInventoryProducts().remove(product);
            }
            product.getCategories().clear();

            // Now delete the product
            inventoryProductRepo.delete(product);

            msg.put("message", "Product deleted successfully");
        } else {
            msg.put("error", "Product not found");
        }

        return msg;
    }


    public HashMap<Object, Object> getAllProducts(Pageable pageable) {
        HashMap<Object, Object> msg = new HashMap<>();
        Page<InventoryProducts> productsPage = inventoryProductRepo.findAll(pageable);
        msg.put("data", productsPage.getContent());
        msg.put("totalPages", productsPage.getTotalPages());
        msg.put("totalElements", productsPage.getTotalElements());
        return msg;
    }


public HashMap<Object, Object> getProductById(Long id){
    HashMap<Object,Object> msg = new HashMap<Object,Object>();
    Optional<InventoryProducts> product= inventoryProductRepo.findById(id);
    if(product.isPresent())
        msg.put("data", product.get());
    else  msg.put("error","can not find product");
    return msg;
}
public HashMap<Object,Object> getProductByBarcode(String barcode){
    HashMap<Object,Object> msg = new HashMap<Object,Object>();
    InventoryProducts product= inventoryProductRepo.findByBarcode(barcode);
  if(product!=null)
      msg.put("data", product);
    else  msg.put("error","can not find product");
    return msg;
}
public HashMap<Object,Object> getProductByName(String name){
    HashMap<Object,Object> msg = new HashMap<Object,Object>();
   List<InventoryProducts> products = inventoryProductRepo.findByNameContainingIgnoreCase(name);
    if (!products.isEmpty())
    msg.put("data", products);
    else  msg.put("error","can not find product");
    return msg;
}



public HashMap<Object,Object> decreaseAmount(Long id){
        HashMap<Object,Object> msg = new HashMap<Object,Object>();
        Optional<InventoryProducts> product= inventoryProductRepo.findById(id);
        if(product.isPresent()) {
            if(product.get().getAmount() > 0)
                product.get().setAmount(product.get().getAmount() - 1);
            else
                msg.put("error","cannot decrease amount below zero");
        }
        else  msg.put("error","can not find product");
    InventoryProducts updatedProduct= inventoryProductRepo.save(product.get());
    SoldProducts soldProducts= new SoldProducts(updatedProduct);
    soldProductsRepo.save(soldProducts);
        msg.put("data", updatedProduct);
        return msg;
}

public HashMap<Object,Object> increaseAmount(Long id){

        HashMap<Object,Object> msg = new HashMap<Object,Object>();
        Optional<InventoryProducts> product= inventoryProductRepo.findById(id);
        if(product.isPresent()) {
            product.get().setAmount(product.get().getAmount() + 1);
            InventoryProducts updatedProduct= inventoryProductRepo.save(product.get());
            msg.put("data", updatedProduct);
        }
        else  msg.put("error","can not find product");
        return msg;
}

public  HashMap<Object,Object> getProductInCategory(ProductDto dto){
        HashMap<Object,Object> msg = new HashMap<Object,Object>();
    Pageable pageable= null;
        Page<InventoryProducts> products = inventoryProductRepo.findAllByCategories(dto.getCategoriesId(), pageable);
        msg.put("data", products);
        return msg;
}


public  HashMap<Object,Object> getProductByCode(String code){

        HashMap<Object,Object> msg = new HashMap<Object,Object>();
        List<InventoryProducts> product= inventoryProductRepo.findByCodeContainingIgnoreCase(code);
        if (!product.isEmpty())
            msg.put("data", product);
        else  msg.put("error","can not find product");
        return  msg;
}

}
