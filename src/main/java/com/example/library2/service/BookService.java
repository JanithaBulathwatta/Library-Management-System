package com.example.library2.service;


import com.example.library2.dto.BookDTO;
import com.example.library2.entity.Book;
import com.example.library2.repo.BookRepo;
import com.example.library2.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
