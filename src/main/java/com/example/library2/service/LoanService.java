package com.example.library2.service;

import com.example.library2.dto.LoanDTO;
import com.example.library2.entity.Book;
import com.example.library2.entity.Loan;
import com.example.library2.entity.User;
import com.example.library2.repo.BookRepo;
import com.example.library2.repo.LoanRepo;
import com.example.library2.repo.UserRepo;
import com.example.library2.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserRepo userRepo;



//    public Loan createLoan(LoanDTO loanDTO) {
//        Book book = bookRepo.findById(loanDTO.getBookID())
//                .orElseThrow(() -> new RuntimeException("Book not found"));
//
//        if(!"not available".equals(book.getStatus())){
//            throw new RuntimeException("Book is not available");
//        }
//
//        User user = userRepo.findById(loanDTO.getUserID())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Loan loan = new Loan();
//        loan.setBook(book);
//        loan.setUser(user);
//
//        return loanRepo.save(loan);
//    }

    public String saveLoan(LoanDTO loanDTO) {
        Optional<User> userOptional = userRepo.findById(loanDTO.getUserID());
        Optional<Book> bookOptional = bookRepo.findById(loanDTO.getBookID());

        if (userOptional.isEmpty()) {
            return VarList.RSP_NO_DATA_FOUND; // User not found
        }
        if (bookOptional.isEmpty()) {
            return VarList.RSP_NO_DATA_FOUND; // Book not found
        }

        Book book = bookOptional.get();
        if ("not available".equals(book.getStatus())) {
            // Automatically create a loan if the book's status is "not available"
            Loan loan = new Loan();
            loan.setUser(userOptional.get());
            loan.setBook(book);
            loan.setBorrowDate(LocalDate.now()); // Set the current date

            loanRepo.save(loan); // Save loan to the database
            return VarList.RSP_SUCCESS; // Return success
        } else {
            return VarList.RSP_ERROR; // Book is available, loan cannot be created
        }
    }

}
