package com.example.inventory.inventory.outputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class OutputsService {
@Autowired
    OutputsRepo outputsRepo;

public HashMap<Object,Object> addOutputs(String output, Double value){
    Outputs outputs = new Outputs();
    outputs.setOutput(output);
    outputs.setValue(value);
    outputsRepo.save(outputs);

    HashMap<Object,Object> result = new HashMap<>();
    result.put("data", outputs);
    return result;
}
public HashMap<Object,Object> updateOutput(Long id,String output, Double value){

    Optional<Outputs> outputs = outputsRepo.findById(id);
    if (output!=null)
    outputs.get().setOutput(output);
    if (value!=null)
    outputs.get().setValue(value);

    outputsRepo.save(outputs.get());

    HashMap<Object,Object> result = new HashMap<>();
    result.put("data", outputs);
    return result;
}
    public HashMap<Object, Object> getAll(Pageable pageable) {
        HashMap<Object, Object> result = new HashMap<>();
        Page<Outputs> outputsPage = outputsRepo.findAll(pageable);
        result.put("data", outputsPage.getContent()); // Extracting the list of outputs
        result.put("totalPages", outputsPage.getTotalPages()); // Total pages
        result.put("totalElements", outputsPage.getTotalElements()); // Total elements
        return result;


    }

public HashMap<Object,Object> deleteOutput(Long id) {
    HashMap<Object,Object> result = new HashMap<>();
    outputsRepo.deleteById(id);
    result.put("data", "Output deleted successfully.");
    return result;
}
}
