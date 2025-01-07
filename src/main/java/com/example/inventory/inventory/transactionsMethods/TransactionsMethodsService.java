package com.example.inventory.inventory.transactionsMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsMethodsService {

    @Autowired
    private TransactionsMethodsRepo transactionsMethodsRepo;

    public HashMap<String, Object> addMethod(String methodName, Double total) {
        HashMap<String, Object> response = new HashMap<>();
        if (methodName == null || methodName.trim().isEmpty()) {
            response.put("data", "Method name cannot be empty");
            return response;
        }

        TransactionsMethods method = new TransactionsMethods(methodName, total);
        TransactionsMethods savedMethod = transactionsMethodsRepo.save(method);

        if (savedMethod != null) {
            response.put("data", savedMethod);
        } else {
            response.put("data", "Method not added");
        }

        return response;
    }

    // Retrieve all transaction methods
    public List<TransactionsMethods> getAllMethods() {
        return transactionsMethodsRepo.findAll();
    }

    public HashMap<String, Object> getMethodById(Long id) {
        HashMap<String, Object> response = new HashMap<>();
        Optional<TransactionsMethods> method = transactionsMethodsRepo.findById(id);

        if (method.isPresent()) {
            response.put("data", method.get());
        } else {
            response.put("data", "Method not found");
        }

        return response;
    }

    public HashMap<String, Object> updateMethod(Long id, String methodName,Double total) {
        HashMap<String, Object> response = new HashMap<>();

        if (methodName == null || methodName.trim().isEmpty()) {
            response.put("data", "Method name cannot be empty");
            return response;
        }

        Optional<TransactionsMethods> optionalMethod = transactionsMethodsRepo.findById(id);
        if (optionalMethod.isPresent()) {
            TransactionsMethods method = optionalMethod.get();
            method.setMethodName(methodName);
            if(total!=null) method.setTotal(total);
            TransactionsMethods updatedMethod = transactionsMethodsRepo.save(method);
            response.put("data", updatedMethod);
        } else {
            response.put("data", "Method not found");
        }

        return response;
    }

    public HashMap<String, Object> deleteMethod(Long id) {
        HashMap<String, Object> response = new HashMap<>();

        if (transactionsMethodsRepo.existsById(id)) {
            transactionsMethodsRepo.deleteById(id);
            response.put("data", "Method deleted successfully");
        } else {
            response.put("data", "Method not found");
        }

        return response;
    }

    public HashMap<String, Object> findMethodByName(String methodName) {
        HashMap<String, Object> response = new HashMap<>();

        if (methodName == null || methodName.trim().isEmpty()) {
            response.put("data", "Method name cannot be empty");
            return response;
        }

        List<TransactionsMethods> method = transactionsMethodsRepo.findByMethodNameContainingIgnoreCase(methodName);
        if (method != null) {
            response.put("data", method);
        } else {
            response.put("data", "Method not found");
        }

        return response;
    }
}
