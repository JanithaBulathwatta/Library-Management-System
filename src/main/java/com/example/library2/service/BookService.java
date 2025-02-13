package com.example.library2.service;


import com.example.library2.dto.BookDTO;
import com.example.library2.entity.Book;
import com.example.library2.repo.BookRepo;
import com.example.library2.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveBook(BookDTO bookDTO) {
        if(bookRepo.existsById(bookDTO.getId())){
            return VarList.RSP_DUPLICATE;
        }else{
            bookRepo.save(modelMapper.map(bookDTO, Book.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String UpdateBook(BookDTO bookDTO) {
        if(bookRepo.existsById(bookDTO.getId())){
            bookRepo.save(modelMapper.map(bookDTO, Book.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;

        }
    }

    public List<BookDTO> getAllBooks() {
       List<Book> bookList = bookRepo.findAll();
       return modelMapper.map(bookList, new TypeToken<ArrayList<BookDTO>>() {
       }.getType());
    }

    public BookDTO searchBook(int id) {
        if(bookRepo.existsById(id)){
            Book book = bookRepo.findById(id).orElse(null);
            return modelMapper.map(book, BookDTO.class);
        }else{
            return null;
        }
    }
    public String deleteBook(int id) {
        if(bookRepo.existsById(id)){
            bookRepo.deleteById(id);
            return VarList.RSP_SUCCESS;
        }
        return VarList.RSP_NO_DATA_FOUND;

    }
}
