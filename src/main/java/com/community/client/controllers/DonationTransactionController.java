package com.community.client.controllers;
import com.community.client.models.DonationTransaction;
import com.community.client.services.DonationTransactionService;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
public class DonationTransactionController {
    //DI for the service layer
    private final DonationTransactionService donationTransactionService;

    public DonationTransactionController(DonationTransactionService donationTransactionService) {
        this.donationTransactionService = donationTransactionService;
    }

    //API endpoints will be defined here
    //1. End point is to save the transaction
    @PostMapping("/api/new-transaction")
    public DonationTransaction saveNewTransaction(@RequestBody DonationTransaction donationTransaction){
        System.out.println("I am being hit");
        return donationTransactionService.saveTransaction(donationTransaction);
    }

    //end point to get one project by id
    @GetMapping("/api/get-project-transaction/{id}")
    private Set<DonationTransaction> getProjectById(@PathVariable String id){
        return donationTransactionService.getProjectById(id);
    }

}
