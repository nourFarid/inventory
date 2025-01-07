package com.example.inventory.inventory.transactions;

import com.example.inventory.inventory.transactionsMethods.TransactionsMethods;
import com.example.inventory.inventory.transactionsMethods.TransactionsMethodsRepo;
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
public class TransactionsService {

    @Autowired
    private TransactionsRepo transactionsRepo;
    @Autowired private TransactionsMethodsRepo transactionsMethodsRepo;

    // Add a new transaction
    public HashMap<String, Object> add(TransactionDto dto) {
        HashMap<String, Object> response = new HashMap<>();
        Transactions transaction = new Transactions(dto);
        Transactions savedTransaction = transactionsRepo.save(transaction);
        Optional<TransactionsMethods> method= transactionsMethodsRepo.findById(dto.getMethodId());
        TransactionsMethods methods=method.get();
        Double newTotal=0.0;
        if(dto.getIsDebit()==true){
            newTotal= methods.getTotal()+dto.getTotal();
        }


        else
 newTotal=   methods.getTotal()-dto.getTotal();
        methods.setTotal(newTotal);
        transactionsMethodsRepo.save(methods);
        response.put("data", savedTransaction);
        return response;
    }

    public HashMap<String, Object> getAllTransactions(int page, int size) {
        HashMap<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size); // Use Pageable to define page number and size
        Page<Transactions> transactionsPage = transactionsRepo.findAll(pageable);
        response.put("data", transactionsPage.getContent());
        response.put("totalPages", transactionsPage.getTotalPages());
        response.put("currentPage", transactionsPage.getNumber());
        return response;
    }

    public HashMap<String, Object> getTransactionById(Long id) {
        HashMap<String, Object> response = new HashMap<>();
        Optional<Transactions> transaction = transactionsRepo.findById(id);

        if (transaction.isPresent()) {
            response.put("data", transaction.get());
        } else {
            response.put("data", "Transaction not found");
        }

        return response;
    }

    public HashMap<String, Object> updateTransaction(Long id, TransactionDto dto) {
        HashMap<String, Object> response = new HashMap<>();

        Optional<Transactions> optionalTransaction = transactionsRepo.findById(id);
        if (optionalTransaction.isPresent()) {
            Transactions transaction = optionalTransaction.get();

           if(dto.getTotal()!=null)
               transaction.setTotal(dto.getTotal());
            if(dto.getRevenue()!=null)
               transaction.setRevenue(dto.getRevenue());
//            if(dto.getService()!=null)
//                transaction.setService(dto.getService());
            if(dto.getIsDebit()!=null)
                transaction.setIsDebit(dto.getIsDebit());



            Transactions updatedTransaction = transactionsRepo.save(transaction);
            response.put("data", updatedTransaction);
        } else {
            response.put("data", "Transaction not found");
        }

        return response;
    }

    public HashMap<String, Object> deleteTransaction(Long id) {
        HashMap<String, Object> response = new HashMap<>();

        if (transactionsRepo.existsById(id)) {
            transactionsRepo.deleteById(id);
            response.put("data", "Transaction deleted successfully");
        } else {
            response.put("data", "Transaction not found");
        }

        return response;
    }


    public HashMap<String, Object> getDebit(int page, int size) {
        HashMap<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<Transactions> transactionsPage = transactionsRepo.findAllByIsDebit(true, pageable);
        response.put("data", transactionsPage.getContent());
        response.put("totalPages", transactionsPage.getTotalPages());
        response.put("currentPage", transactionsPage.getNumber());
        return response;
    }

    public HashMap<Object, Object> getCredit(int page, int size) {
        HashMap<Object, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<Transactions> transactionsPage = transactionsRepo.findAllByIsDebit(false, pageable);
        response.put("data", transactionsPage.getContent());
        response.put("totalPages", transactionsPage.getTotalPages());
        response.put("currentPage", transactionsPage.getNumber());
        return response;
    }
    public HashMap<Object,Object> getByDate(String date, int page, int size) {
        HashMap<Object, Object> msg = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        Pageable pageable = PageRequest.of(page, size);
Page<Transactions> totalTransactions= transactionsRepo.findAllByDate(localDate,pageable);

if(totalTransactions.hasContent()){
    msg.put("data", totalTransactions.getContent());
    msg.put("totalPages", totalTransactions.getTotalPages());
    msg.put("currentPage", totalTransactions.getNumber());
    msg.put("totalElements", totalTransactions.getTotalElements());
}
else
    msg.put("error", "No data found for the selected date.");
return msg;

    }
}
