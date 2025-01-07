package com.example.inventory.inventory.categories;

import com.example.inventory.inventory.categories.dto.CategoriesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service

public class CategoriesService {
  @Autowired
    private  CategoriesRepo categoriesRepo;

public HashMap<Object,Object> addCategory(CategoriesDto dto)
{
    HashMap<Object,Object> msg= new HashMap<>();
    Categories category=new Categories(dto.getName());
Categories savedCategory= categoriesRepo.save(category);

    if(savedCategory!= null)
        msg.put("data", savedCategory);
    else
        msg.put("error","can not add category");

    return msg;
}

public  HashMap<Object, Object> updateCategory(Long id, CategoriesDto dto) {
    HashMap<Object,Object> msg= new HashMap<>();

    Optional<Categories> categories= categoriesRepo.findById(id);
    if (categories.isPresent()) {
        categories.get().setName(dto.getName());
        categoriesRepo.save( categories.get());
        msg.put("data", categories);

    }
    else         msg.put("error","can not add category");
return  msg;
}

public  HashMap<Object, Object> deleteCategory(Long id) {
    HashMap<Object,Object> msg= new HashMap<>();

    Optional<Categories> categories= categoriesRepo.findById(id);
    if (categories.isPresent()) {
        categoriesRepo.delete(categories.get());
        msg.put("data", "category deleted successfully");
    }
    else         msg.put("error","can not delete category");
    return  msg;
}

public  HashMap<Object, Object> getAllCategories() {
    HashMap<Object,Object> msg= new HashMap<>();
    Iterable<Categories> categories= categoriesRepo.findAll();
    msg.put("data", categories);
    return  msg;
}

public  HashMap<Object,Object> getCategorById(Long id){
    HashMap<Object,Object> msg= new HashMap<>();
    Optional<Categories> categories= categoriesRepo.findById(id);
    if (categories.isPresent()) {
        msg.put("data", categories);
    }
    else         msg.put("error","can not find category");
    return  msg;
}

public HashMap<Object,Object> getCategoryByName(CategoriesDto dto) {
    HashMap<Object,Object> msg= new HashMap<>();
    Categories categories= categoriesRepo.findByName(dto.getName());
    if (categories!= null) {
        msg.put("data", categories);
    }
    else         msg.put("error","can not find category");
    return  msg;

}
}
