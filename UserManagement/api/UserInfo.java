package com.tuukkal.talousappi.UserManagement.api;


import com.tuukkal.talousappi.UserManagement.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserInfo {

    private final TransactionService transactionService;


    @GetMapping(value = "/years")
    public Collection getYears(@AuthenticationPrincipal String username) {
        System.out.println(username);
        return transactionService.getYears(username);
    }

    @GetMapping(path = "/months/{year}")
    public Collection getMonths(@AuthenticationPrincipal String username, @PathVariable String year) {
        return transactionService.getMonths(username, year);
    }


    @GetMapping(path = "/monthdata/{year}/{month}")
    public Map<String, List<Collection>> getMonthData(@AuthenticationPrincipal String username,
                                                      @PathVariable String year,
                                                      @PathVariable String month)
    {
        System.out.println(year +  month);
        return transactionService.getMonthData(username, year, month);
    }

}
