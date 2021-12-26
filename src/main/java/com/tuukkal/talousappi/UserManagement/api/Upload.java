package com.tuukkal.talousappi.UserManagement.api;

import com.tuukkal.talousappi.UserManagement.service.TypeService;
import com.tuukkal.talousappi.UserManagement.service.TypeTagService;
import com.tuukkal.talousappi.UserManagement.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Upload {

    private final UploadService uploadService;
    private final TypeTagService typeTagService;
    private final TypeService typeService;

    @PostMapping(path = "/uploadcsv")
    public ArrayList< ArrayList <String > > uploadCSVFile(@AuthenticationPrincipal String username, @RequestParam("file") MultipartFile file) {
        return uploadService.readFile(username, file);
    }

    @PostMapping(path = "/tags")
    public ResponseEntity<?> addTags(@AuthenticationPrincipal String username, @RequestParam Map<String,String> tags) {
        return typeTagService.addTags(username, tags);
    }

    @PostMapping(value = "/transactions")
    public ResponseEntity<?> newTransactions(@AuthenticationPrincipal String username, @RequestParam Map<String,String> transactions) {
        return uploadService.saveTranactions(username, transactions);
    }

    @GetMapping(value = "/types")
    public ResponseEntity<?> getTypes(){
        return ResponseEntity.ok().body(typeService.getAll());
    }

}


