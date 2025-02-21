package com.example.library2.controller;


import com.example.library2.dto.LoanDTO;
import com.example.library2.entity.Loan;
import com.example.library2.service.LoanService;
import com.example.library2.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

//    @PostMapping("/createLone")
//    public ResponseEntity<Loan> createLoan(@RequestBody LoanDTO loanDTO) {
//        Loan loan = loanService.createLoan(loanDTO);
//        return ResponseEntity.ok(loan);
//    }

    @PostMapping("/createLoan")
    public ResponseEntity<String> createLoan(@RequestBody LoanDTO loanDTO) {
        // Call the service to create the loan
        String response = loanService.saveLoan(loanDTO);

        // Return the response based on the service result
        if (VarList.RSP_SUCCESS.equals(response)) {
            return new ResponseEntity<>("Loan successfully created", HttpStatus.CREATED);
        } else if (VarList.RSP_NO_DATA_FOUND.equals(response)) {
            return new ResponseEntity<>("User or Book not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Book is not available for loan", HttpStatus.BAD_REQUEST);
        }
    }

    // Optional: Endpoint to get all loans
//    @GetMapping
//    public ResponseEntity<List<LoanDTO>> getAllLoans() {
//        List<LoanDTO> loans = loanService.getAllLoans();
//        return new ResponseEntity<>(loans, HttpStatus.OK);
//    }
}
