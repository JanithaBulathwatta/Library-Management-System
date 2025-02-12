package com.example.library2.repo;


import com.example.library2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface BookRepo extends JpaRepository<Book, Integer> {
}
