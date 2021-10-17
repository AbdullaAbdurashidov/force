package com.code.force.repository;

import com.code.force.domain.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    @Transactional
    @Modifying
    @Query(value = "update books set subject=:subject where bookid=:bookID", nativeQuery = true)
    void updateSubjectNative(@Param("subject") String subject, @Param("bookID") Long bookID);

    @Transactional
    @Modifying
    @Query(value = "update books set book_author=:bookAuthor where bookid=:bookID", nativeQuery = true)
    void updateBookAuthorNative(@Param("bookAuthor") String bookAuthor, @Param("bookID") Long bookID);

    @Transactional
    @Modifying
    @Query(value = "update books set bookisbn=:bookISBN where bookid=:bookID", nativeQuery = true)
    void updateBookISBNNative(@Param("bookISBN") String bookISBN, @Param("bookID") Long bookID);

    @Transactional
    @Modifying
    @Query(value = "update books set book_title=:bookTitle where bookid=:bookID", nativeQuery = true)
    void updateBookTitleNative(@Param("bookTitle") String bookTitle, @Param("bookID") Long bookID);


    @Transactional
    @Modifying
    @Query(value = "update books set publish_date=:publishDate where bookid=:bookID", nativeQuery = true)
    void updatePublishDateNative(@Param("publishDate") String publishDate, @Param("bookID") Long bookID);


    @Transactional
    @Modifying
    @Query(value = "update books set borrowed=:borrowed where bookid=:bookID", nativeQuery = true)
    void updateBorrowedNative(@Param("borrowed") String borrowed, @Param("bookID") Long bookID);
    @Transactional
    @Modifying
    @Query(value = "update books set borrowed_by=:borrowedBy where bookid=:bookID", nativeQuery = true)
    void updateBorrowedByNative(@Param("borrowedBy") String borrowedBy, @Param("bookID") Long bookID);


    @Transactional
    @Modifying
    @Query(value = "update books set reserved=:reserved where bookid=:bookID", nativeQuery = true)
    void updateReservedNative(@Param("reserved") String reserved, @Param("bookID") Long bookID);
    @Transactional
    @Modifying
    @Query(value = "update books set reserved_by=:reservedBy where bookid=:bookID", nativeQuery = true)
    void updateReservedByNative(@Param("reservedBy") String reservedBy, @Param("bookID") Long bookID);


    @Transactional
    @Modifying
    @Query(value = "update books set details=:details where bookid=:bookID", nativeQuery = true)
    void updateDetailsNative(@Param("details") String details, @Param("bookID") Long bookID);


    @Transactional
    @Modifying
    @Query(value = "update books set issue_date=:issueDate where bookid=:bookID", nativeQuery = true)
    void updateIssueDateNative(@Param("issueDate") String issueDate, @Param("bookID") Long bookID);
    @Transactional
    @Modifying
    @Query(value = "update books set due_date=:dueDate where bookid=:bookID", nativeQuery = true)
    void updateDueDateNative(@Param("dueDate") String dueDate, @Param("bookID") Long bookID);
}
