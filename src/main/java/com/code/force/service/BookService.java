package com.code.force.service;


import com.code.force.domain.Books;
import com.code.force.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Books addBook(Books book){
        Books book1=new Books();

        book1.setBookID(book.getBookID());
        book1.setSubject(book.getSubject());
        book1.setBookAuthor(book.getBookAuthor());
        book1.setPublishDate(book.getPublishDate());
        book1.setBookTitle(book.getBookTitle());
        book1.setBookISBN(book.getBookISBN());
        book1.setBorrowed(book.getBorrowed());
        book1.setBorrowedBy(book.getBorrowedBy());
        book1.setReserved(book.getReserved());
        book1.setReservedBy(book.getReservedBy());
        book1.setDetails(book.getDetails());
        book1.setDueDate(book.getDueDate());
        book1.setIssueDate(book.getIssueDate());
        if(bookRepository.save(book1)!=null)
            return book;
        else
            return null;
    }

    public List<Books> getAllBooks(){
        List<Books> booksList=bookRepository.findAll();
        return booksList;
    }

    public void delete(Long id){
        Books book=bookRepository.getOne(id);
        bookRepository.delete(book);
    }


    public void updateSubjectByID(Long bookID,String subject){
        bookRepository.updateSubjectNative(subject,bookID);
    }

    public void updateBookTitleByID(Long bookID,String title){
        bookRepository.updateBookTitleNative(title,bookID);
    }

    public void updateBookAuthorByID(Long bookID,String author){
        bookRepository.updateBookAuthorNative(author,bookID);
    }

    public void updateBookISBNByID(Long bookID,String isbn){
        bookRepository.updateBookISBNNative(isbn,bookID);
    }

    public void updateBookPublishDateByID(Long bookID,String publishDate){
        bookRepository.updatePublishDateNative(publishDate,bookID);
    }

    public void updateBorrowedByID(Long bookID,String borrowed){
        bookRepository.updateBorrowedNative(borrowed,bookID);
    }

    public void updateBorrowedByByID(Long bookID,String borrowedBy){
        bookRepository.updateBorrowedByNative(borrowedBy,bookID);
    }

    public void updateReservedByID(Long bookID,String reserved){
        bookRepository.updateReservedNative(reserved,bookID);
    }

    public void updateReservedByByID(Long bookID,String reservedBy){
        bookRepository.updateReservedByNative(reservedBy,bookID);
    }

    public void updateIssueDateByID(Long bookID,String issueDate){
        bookRepository.updateIssueDateNative(issueDate,bookID);
    }

    public void updateDueDateByID(Long bookID,String dueDate){
        bookRepository.updateDueDateNative(dueDate,bookID);
    }

    public void updateDetailsByID(Long bookID,String details){
        bookRepository.updateDetailsNative(details,bookID);
    }
}
