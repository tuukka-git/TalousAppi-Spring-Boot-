package com.tuukkal.talousappi.repo;

import com.tuukkal.talousappi.UserManagement.domain.Transaction;
import com.tuukkal.talousappi.UserManagement.domain.Type;
import com.tuukkal.talousappi.UserManagement.domain.User;
import com.tuukkal.talousappi.UserManagement.repo.TransactionRepo;
import com.tuukkal.talousappi.UserManagement.repo.TypeRepo;
import com.tuukkal.talousappi.UserManagement.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.ArrayList;

@SpringBootTest
public class TransactionRepoTest {

    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private TypeRepo typeRepo;
    @Autowired
    private UserRepo userRepo;

    @Test
    public void saveTransaction() {
        Transaction transaction = Transaction.builder()
                .date(Date.valueOf("2021-01-01"))
                .sum(20.00)
                .target("testi")
                .build();
        transactionRepo.save(transaction);
        Type type = Type.builder()
                .type_id(0L)
                .typeName("MUU")
                .build();
        typeRepo.save(type);
        transaction.setType(typeRepo.findById(0L).get());
    }

    @Test
    public void getYears() {
        User user = new User(null, "testi", "1234",  new ArrayList<>(), new ArrayList<>());
        userRepo.save(user);
        transactionRepo.save( new Transaction(null, Date.valueOf("2021-01-01"), 20.00, "testi", user, null));
        transactionRepo.save( new Transaction(null, Date.valueOf("2022-01-01"), 20.00, "testi", user, null));
        transactionRepo.save( new Transaction(null, Date.valueOf("2023-01-01"), 20.00, "testi", user, null));
        System.out.println(transactionRepo.getYears(user.getId()));
    }
    @Test
    public void getMonths() {
        User user = new User(null, "Tuukka", "1234", new ArrayList<>(), new ArrayList<>());
        userRepo.save(user);
        transactionRepo.save( new Transaction(null, Date.valueOf("2021-01-01"), 20.00, "testi", user, null));
        transactionRepo.save( new Transaction(null, Date.valueOf("2021-02-01"), 20.00, "testi", user, null));
        transactionRepo.save( new Transaction(null, Date.valueOf("2021-03-01"), 20.00, "testi", user, null));
        System.out.println(transactionRepo.getMoths(user.getId(), 2021));
    }
    @Test
    public void getMonthData() {
        User user = new User(null, "testi", "1234", new ArrayList<>(), new ArrayList<>());
        userRepo.save(user);
        Type muu = new Type(0L,"Muu");
        typeRepo.save(muu);
        Type ruoka = new Type(1L,"Ruoka");
        typeRepo.save(ruoka);
        transactionRepo.save( new Transaction(null, Date.valueOf("2021-01-01"), 20.00, "Kauppa", user, ruoka));
        transactionRepo.save( new Transaction(null, Date.valueOf("2021-01-01"), 20.00, "Muu", user, muu));
        transactionRepo.save( new Transaction(null, Date.valueOf("2021-01-01"), 20.00, "Kauppa", user, ruoka));
        System.out.println(transactionRepo.getIncome(user.getId(), Date.valueOf("2021-01-01"), Date.valueOf("2021-02-01")));
        System.out.println(transactionRepo.getExpenses(user.getId(), Date.valueOf("2021-01-01"), Date.valueOf("2021-02-01")));
    }


}
