package com.example.inventory.inventory.totalMoneyPerDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TotalMoneyPerDayService {
@Autowired
    private TotalMoneyPerDayRepo totalMoneyPerDayRepo;

public HashMap<Object,Object> addTotalMoneyPerDay(Double totalMoneyPerDay,String source){
    HashMap<Object,Object> msg=  new HashMap<Object,Object>();
TotalMoneyPerDay totalMoney= new TotalMoneyPerDay(totalMoneyPerDay, source);
     TotalMoneyPerDay savedTotalMoney = totalMoneyPerDayRepo.save(totalMoney);
    if (savedTotalMoney!=null){
        msg.put("data", savedTotalMoney);
    }
    else
        msg.put("error","cannot save total money");
    return msg;

}



    public HashMap<String, Object> getTotalMoneyPerDayByDate(String date, int page, int size) {
        HashMap<String, Object> msg = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);

        Pageable pageable = PageRequest.of(page, size);
        Page<TotalMoneyPerDay> totalMoneyPage = totalMoneyPerDayRepo.findByDate(localDate, pageable);

        if (totalMoneyPage.hasContent()) {
            msg.put("data", totalMoneyPage.getContent());
            msg.put("totalPages", totalMoneyPage.getTotalPages());
            msg.put("currentPage", totalMoneyPage.getNumber());
            msg.put("totalElements", totalMoneyPage.getTotalElements());
        } else {
            msg.put("error", "No data found for the selected date.");
        }
        return msg;
    }
public HashMap<Object,Object> updateTotalMoney(Long id,Double totalMoney){

    HashMap<Object,Object> msg=  new HashMap<Object,Object>();
    Optional<TotalMoneyPerDay> total= totalMoneyPerDayRepo.findById(id);

    if (total.isPresent()){
        total.get().setTotalMoney(totalMoney);
        TotalMoneyPerDay savedTotalMoney = totalMoneyPerDayRepo.save(total.get());
        if (savedTotalMoney!=null){
            msg.put("data", savedTotalMoney);
        }
        else
            msg.put("error","cannot update total money");
    }
    else
        msg.put("error","cannot find total money");
    return msg;
}

public HashMap<Object,Object> deleteTotalMoney(Long id){
    HashMap<Object,Object> msg=  new HashMap<Object,Object>();
    Optional<TotalMoneyPerDay> total= totalMoneyPerDayRepo.findById(id);

    if (total.isPresent()){
        totalMoneyPerDayRepo.deleteById(id);
        msg.put("data", "total money deleted");
    }
    else
        msg.put("error","cannot find total money");
    return msg;
}

    public HashMap<Object, Object> getAllTotalMoney(Pageable pageable) {
        HashMap<Object, Object> msg = new HashMap<>();
        Page<TotalMoneyPerDay> totalMoneyPage = totalMoneyPerDayRepo.findAll(pageable);

        if (!totalMoneyPage.isEmpty()) {
            msg.put("data", totalMoneyPage.getContent());
            msg.put("totalPages", totalMoneyPage.getTotalPages());
            msg.put("currentPage", totalMoneyPage.getNumber());
        } else {
            msg.put("error", "no data");
        }
        return msg;
    }

public  HashMap<Object,Object> getTotalMoneyPerDayById(Long id) {

    HashMap<Object,Object> msg=  new HashMap<Object,Object>();
    Optional<TotalMoneyPerDay> total= totalMoneyPerDayRepo.findById(id);
    if (total.isPresent()){
        msg.put("data", total.get());
    }

    else
        msg.put("error", "no data found");
    return msg;
}

}
