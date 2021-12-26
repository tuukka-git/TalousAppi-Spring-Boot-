package com.tuukkal.talousappi.UserManagement.service;

import com.tuukkal.talousappi.UserManagement.domain.Transaction;
import com.tuukkal.talousappi.UserManagement.domain.Type;
import com.tuukkal.talousappi.UserManagement.domain.User;
import com.tuukkal.talousappi.UserManagement.repo.TransactionRepo;
import com.tuukkal.talousappi.UserManagement.repo.TypeRepo;
import com.tuukkal.talousappi.UserManagement.repo.TypetagRepo;
import com.tuukkal.talousappi.UserManagement.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final TransactionRepo transactionRepo;
    private final TypetagRepo typetagRepo;
    private final TypeRepo typeRepo;
    private final UserRepo userRepo;


    public ArrayList< ArrayList <String > > readFile(String username, MultipartFile file){
        Long userId = userRepo.findByUsername(username).getId();
        ArrayList< ArrayList <String> > unfiltered = new ArrayList<ArrayList<String>>();
        HashSet<String> tags = new HashSet();
        String line;
        Boolean skipRow = true;
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            while ((line = br.readLine())!=null) {
                if (skipRow) {
                    skipRow = false;
                    continue;
                }
                String[] row = line.split(";");
                ArrayList<String> SQLrow = filter(row);
                if(typetagRepo.TagExists(userId, SQLrow.get(2))){
                    addToDB(username, SQLrow.get(0), SQLrow.get(1), SQLrow.get(2));
                }else {
                    tags.add(SQLrow.get(2));
                    unfiltered.add(SQLrow);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> t = new ArrayList<>(tags);
        unfiltered.add(0, t);
        return unfiltered;
    }
    public ResponseEntity saveTranactions(String username, Map<String, String> transactions){
        for (String value : transactions.values()) {
            String t = deleteBrackets(value);
            System.out.println(t);
            String[] a = t.split(",");
            addToDB(username, deleteBrackets(a[0]), deleteBrackets(a[1]), deleteBrackets(a[2]));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ArrayList<String> filter(String[] row){
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(toSQLdate(row[0]));
        temp.add(toSQLmoney(row[2]));
        temp.add(toSQLword(row[5]));
        System.out.println(temp);
        return temp;
    }

    private static String toSQLdate(String date){
        String[] dateMonthYear = date.split("\\.");
        String day = dateMonthYear[0];
        String month = dateMonthYear[1];
        String year = dateMonthYear[2];
        if(day.length() == 1){day = '0' + day;}
        if(month.length() == 1){ month = '0' + month;}
        return (year + '-' + month + '-' + day);
    }
    private String toSQLmoney(String sum){
        if(sum.contains(",")){
            String[] Ssum = sum.split(",");
            if(Ssum[1].length() == 2) {
                return (Ssum[0] + "." + Ssum[1]);
            }else {
                return (Ssum[0] + "." + Ssum[1] + "0");
            }
        }else{
            return (sum + ".00");
        }
    }
    private String toSQLword(String word){
        if( word == "" || word.isEmpty()) {
            return "tyhja";
        }
        if( word.contains("\n")){
            return word.substring(0, word.length() - 1);
        }else{
            return word;
        }
    }
    private String deleteBrackets(String s) {
        return s.substring(1, s.length() - 1);
    }

    private void addToDB(String username, String date, String sum, String target) {
        User u = userRepo.findByUsername(username);
        Date d = Date.valueOf(date);
        Double s = Double.valueOf(sum);
        Type t = typeRepo.getTypeWithTag(u.getId(), target);
        System.out.println(t);
        Transaction transaction = Transaction.builder()
                .date(d)
                .sum(s) //double
                .target(target)
                .user(u)
                .type(t)
                .build();

        transactionRepo.save(transaction);
    }



}
