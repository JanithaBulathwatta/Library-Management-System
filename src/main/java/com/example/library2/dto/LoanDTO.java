package com.example.library2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

    private int userID;
    private int bookID;
    private String userName;
    private String bookName;
}
