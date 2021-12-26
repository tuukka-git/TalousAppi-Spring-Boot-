package com.tuukkal.talousappi.UserManagement.service;

import com.tuukkal.talousappi.UserManagement.domain.Transaction;
import com.tuukkal.talousappi.UserManagement.repo.TransactionRepo;
import com.tuukkal.talousappi.UserManagement.repo.TypetagRepo;
import com.tuukkal.talousappi.UserManagement.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Service
@Component
@RequiredArgsConstructor
public class TransactionService {


private final TransactionRepo transactionRepo;
private final UserRepo userRepo;

    public Collection getYears(String username) {
        Long userId = userRepo.findByUsername(username).getId();
        System.out.println(userId);
        System.out.println(transactionRepo.getYears(userId));
        return transactionRepo.getYears(userId);
    }

    public Collection getMonths(String username, String year) {
        Long userId = userRepo.findByUsername(username).getId();
        return transactionRepo.getMoths(userId, Integer.valueOf(year));
    }

    public Map<String, List<Collection>> getMonthData(String username, String year, String month){

        Date fro = Date.valueOf(toDate(year, Integer.valueOf(month), "01"));
        Date to = Date.valueOf(toDate(year, nextMonth(month), "01"));
        Long userId = userRepo.findByUsername(username).getId();

        return Map.of(
                "tulot",transactionRepo.getIncome(userId, fro, to),
                "menot", transactionRepo.getExpenses(userId, fro, to)
        );
    }

    public Transaction saveTransaction(Transaction transaction){
        return transactionRepo.save(transaction);
    }

    private String toDate(String year, Integer month, String day){
        String m = Integer.toString(month);
        if( m.length() == 1) {
            m = "0" + m;
        }
        System.out.println(year + '-' + m + '-' + day);
        return (year + '-' + m + '-' + day); //year-month-00
    }

    private Integer nextMonth(String month){
        int num = Integer.valueOf(month);
        return num + 1;
    }
}


