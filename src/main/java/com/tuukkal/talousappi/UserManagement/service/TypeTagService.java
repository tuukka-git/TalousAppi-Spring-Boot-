package com.tuukkal.talousappi.UserManagement.service;

import com.tuukkal.talousappi.UserManagement.domain.Typetag;
import com.tuukkal.talousappi.UserManagement.domain.User;
import com.tuukkal.talousappi.UserManagement.repo.TypeRepo;
import com.tuukkal.talousappi.UserManagement.repo.TypetagRepo;
import com.tuukkal.talousappi.UserManagement.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TypeTagService {

    private final TypetagRepo typetagRepo;
    private final UserRepo userRepo;
    private final TypeRepo typeRepo;


    public ResponseEntity<?> addTags(String username, Map<String,String> data) {
        System.out.println(data);
        User user = userRepo.findByUsername(username);
        for ( Map.Entry<String, String> entry : data.entrySet() ) {
            Typetag typetag = Typetag.builder()
                    .tag(entry.getKey())
                    .type(typeRepo.findById(Long.parseLong(entry.getValue())).get())
                    .user(user)
                    .build();
            typetagRepo.save(typetag);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Typetag saveTypetag(Typetag typetag) {
        return typetagRepo.save(typetag);
    }

    public boolean TagExists(Long userId, String tag) {
        return typetagRepo.TagExists(userId, tag);
    }

    public List<Typetag> getTypetags() {
        return typetagRepo.findAll();
    }

    private String deleteBrackets(String s) {
        return s.substring(1, s.length() - 1);
    }
}
