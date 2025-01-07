package com.example.inventory.inventory.soldProducts;

import com.example.inventory.inventory.products.dto.ProductDto;
import com.example.inventory.inventory.totalMoneyPerDay.TotalMoneyPerDayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SoldProductsService {
    @Autowired
    private SoldProductsRepo soldProductsRepo;
    @Autowired private TotalMoneyPerDayRepo totalMoneyPerDayRepo;
    public HashMap<Object, Object> getSoldProducts(int page, int size) {
        HashMap<Object, Object> msg = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size); // Create a pageable object
        Page<SoldProducts> soldProductsPage = soldProductsRepo.findAll(pageable); // Fetch paginated data

        msg.put("data", soldProductsPage.getContent()); // List of items
        msg.put("currentPage", soldProductsPage.getNumber()); // Current page number
        msg.put("totalPages", soldProductsPage.getTotalPages()); // Total number of pages
        msg.put("totalItems", soldProductsPage.getTotalElements()); // Total number of items
        return msg;
    }

public HashMap<Object,Object> getSoldProductsByDate(String date){
    HashMap<Object,Object> msg = new HashMap<Object,Object>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(date, formatter);

        List<SoldProducts> soldProducts=soldProductsRepo.findAllByDate(localDate);
        msg.put("data",soldProducts);

        return  msg;
}
public  HashMap<Object,Object> delete(Long id)
{
    HashMap<Object,Object> msg = new HashMap<>();
    try {
        soldProductsRepo.deleteById(id);
        msg.put("data", "Sold product deleted successfully");
    } catch (Exception e) {
        msg.put("error", e.getMessage());
    }
    return msg;
}
public HashMap<Object, Object> update(ProductDto dto) {
        HashMap<Object, Object> msg = new HashMap<Object, Object>();
        Optional<SoldProducts> product= soldProductsRepo.findById(dto.getId());
        if(product.isPresent()){
            if (dto.getName() != null)
                product.get().setName(dto.getName());
            if (dto.getPrice() != null)
                product.get().setPrice(dto.getPrice());
            if(dto.getDiscount() != null)
                product.get().setDiscount(dto.getDiscount());
            if(dto.getAmount() != null)
                product.get().setAmount(dto.getAmount());
            if(dto.getDescription() != null)
                product.get().setDescription(dto.getDescription());
            if(dto.getCode() != null)
                product.get().setCode(dto.getCode());
            SoldProducts updatedProduct= soldProductsRepo.save(product.get());
            msg.put("data", updatedProduct);
        }

               else  msg.put("error","can not find product");
    return msg;

}
}
