package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // Your code goes here.
        book.setId(id);
        this.bookList.add(book);
        this.id++;
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()
    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book>getBookById(@PathVariable int id){
        Book ans = new Book();
        int n = bookList.size();
        for(int i =0;i<n;i++){
            if(bookList.get(i).getId()==id){
                ans=bookList.get(i);
                break;
            }

        }
        return new ResponseEntity<>(ans,HttpStatus.ACCEPTED);
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity deleteBookById(@PathVariable int id){
        int n = bookList.size();
        for(int i =0;i<n;i++){
            if(bookList.get(i).getId()==id){
                bookList.remove(i);
                break;
            }
        }
        return new ResponseEntity<>("Deleted",HttpStatus.ACCEPTED);
    }

    // get request /get-all-books
    // getAllBooks()
    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>>getAllBooks(){
        return new ResponseEntity<>(this.bookList,HttpStatus.ACCEPTED);
    }

    // delete request /delete-all-books
    // deleteAllBooks()
    @DeleteMapping("/delete-all-books")
    public ResponseEntity deleteAllBooks(){
        this.bookList.clear();
        return new ResponseEntity<>("all deleted",HttpStatus.ACCEPTED);
    }

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>>getBooksByAuthor(@RequestParam String author){
        int n = bookList.size();
        List<Book>ans = new ArrayList<>();
        for(int i =0;i<n;i++){
            if(bookList.get(i).getAuthor().equals(author)){
                ans.add(bookList.get(i));
            }
        }
        return new ResponseEntity<>(ans,HttpStatus.ACCEPTED);
    }


    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>>getBooksByGenre(@RequestParam String genre){
        int n = bookList.size();
        List<Book>ans = new ArrayList<>();
        for(int i =0;i<n;i++){
            if(bookList.get(i).getGenre().equals(genre)){
                ans.add(bookList.get(i));
            }
        }
        return new ResponseEntity<>(ans,HttpStatus.ACCEPTED);
    }
}
