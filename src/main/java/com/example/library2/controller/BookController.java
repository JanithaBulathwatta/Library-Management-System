package com.example.library2.controller;


import com.example.library2.dto.BookDTO;
import com.example.library2.dto.ResponseDTO;
import com.example.library2.service.BookService;
import com.example.library2.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/addBook")
    public ResponseEntity addBook(@RequestBody BookDTO bookDTO) {


        try{
            String res = bookService.saveBook(bookDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(bookDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATE);
                responseDTO.setMessage("Employee Registered");
                responseDTO.setContent(bookDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);


            }else{

                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);


            }

        }catch(Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


}
