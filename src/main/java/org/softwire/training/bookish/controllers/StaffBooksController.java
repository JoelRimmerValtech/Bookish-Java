package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.BookModel;
import org.softwire.training.bookish.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/staff/books")
public class StaffBooksController {

    private final BookService bookService;

    @Autowired
    public StaffBooksController(BookService bookService) {
        this.bookService = new BookService();
    }

    @RequestMapping("")
    ModelAndView books() {
        List<Book> bookList = bookService.getBooks("","");
        BookModel bookModel = new BookModel();
        bookModel.setBookList(bookList);
        return new ModelAndView("staffBooks", "books", bookModel);
    }

    @RequestMapping("/add")
    RedirectView addBooks(@ModelAttribute Book book) {
        bookService.addBook(book);
        return new RedirectView("/staff/books");
    }

    @RequestMapping("/edit")
    RedirectView editBooks(@RequestBody Book book) {
        bookService.editBook(book);
        return new RedirectView("/staff/books");
    }

    @RequestMapping("/delete")
    RedirectView deleteBooks(@RequestBody Book book) {
        bookService.deleteBook(book.getBookId());
        return new RedirectView("/staff/books");
    }

}
