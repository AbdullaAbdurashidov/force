package com.code.force.controller;

import com.code.force.domain.Books;
import com.code.force.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RequestMapping("/api")
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/books/add")
    public ResponseEntity addBook(@RequestBody Books book){
        Books book1=bookService.addBook(book);
        return ResponseEntity.ok(book1);
    }

    @GetMapping("/books/getAll")
    public ResponseEntity getAllBooks(){
        List<Books> booksList=bookService.getAllBooks();
        return ResponseEntity.ok(booksList);
    }

    @DeleteMapping("/books/delete/{id}")
    public ResponseEntity deleteUserByID(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok("Malumot ochirildi");
    }

    @PutMapping("/books/update/subject/{id}/{subject}")
    public ResponseEntity updateSubjectById(@PathVariable Long id, @PathVariable String subject){
        bookService.updateSubjectByID(id,subject);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/books/update/author/{id}/{author}")
    public ResponseEntity updateAuthorById(@PathVariable Long id, @PathVariable String author){
        bookService.updateBookAuthorByID(id,author);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/books/update/isbn/{id}/{isbn}")
    public ResponseEntity updateIsbnById(@PathVariable Long id, @PathVariable String isbn){
        bookService.updateBookISBNByID(id,isbn);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/books/update/title/{id}/{title}")
    public ResponseEntity updateTitleById(@PathVariable Long id, @PathVariable String title){
        bookService.updateBookTitleByID(id,title);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/books/update/publishDate/{id}/{publishDate}")
    public ResponseEntity updatePublishDateById(@PathVariable Long id, @PathVariable String publishDate){
        bookService.updateBookPublishDateByID(id,publishDate);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/books/update/borrowed/{id}/{borrowed}")
    public ResponseEntity updateBorrowedById(@PathVariable Long id, @PathVariable String borrowed){
        bookService.updateBorrowedByID(id,borrowed);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/books/update/borrowedBy/{id}/{borrowedBy}")
    public ResponseEntity updateBorrowedByById(@PathVariable Long id, @PathVariable String borrowedBy){
        bookService.updateBorrowedByByID(id,borrowedBy);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/books/update/reserved/{id}/{reserved}")
    public ResponseEntity updateReservedById(@PathVariable Long id, @PathVariable String reserved){
        bookService.updateReservedByID(id,reserved);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/books/update/reservedBy/{id}/{reservedBy}")
    public ResponseEntity updateReservedByById(@PathVariable Long id, @PathVariable String reservedBy){
        bookService.updateReservedByByID(id,reservedBy);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/books/update/details/{id}/{details}")
    public ResponseEntity updateDetailsById(@PathVariable Long id, @PathVariable String details){
        bookService.updateDetailsByID(id,details);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/books/update/issueDate/{id}/{issueDate}")
    public ResponseEntity updateIssueDateById(@PathVariable Long id, @PathVariable String issueDate){
        bookService.updateIssueDateByID(id,issueDate);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

    @PutMapping("/books/update/dueDate/{id}/{dueDate}")
    public ResponseEntity updateDueDateById(@PathVariable Long id, @PathVariable String dueDate){
        bookService.updateDueDateByID(id,dueDate);
        return ResponseEntity.ok(HttpServletResponse.SC_OK);
    }

}
