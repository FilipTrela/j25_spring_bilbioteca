package pl.sda.gdajava25.spring.bilbioteka.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.gdajava25.spring.bilbioteka.model.BookLent;
import pl.sda.gdajava25.spring.bilbioteka.service.BookLentService;
import pl.sda.gdajava25.spring.bilbioteka.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;


@Controller
@AllArgsConstructor
@RequestMapping(path = "/booklent/")
public class BookLentController {

    private final BookLentService bookLentService;
    private final BookService bookService;

    @GetMapping("/add/{id}")
    public String getForm(Model model, BookLent bookLent, @PathVariable(name = "id") Long id) {
        model.addAttribute("books", bookService.getAllBook());
        model.addAttribute("clientId", id);
        model.addAttribute("bookLent", bookLent);

        return "bookLent-add";
    }

    @PostMapping("/add")
    public String add(BookLent bookLent, Long clientId, Long bookId) {

        bookLentService.add(bookLent, clientId, bookId);

        return "redirect:/reader/blList/" + clientId;
    }

    @GetMapping("/return/{id}")
    public String returnBook(Model model, @PathVariable(name = "id") Long id, HttpServletRequest request) {
        Optional<BookLent> optionalBookLent = bookLentService.findById(id);
        if (optionalBookLent.isPresent()) {
            BookLent bookLent = optionalBookLent.get();
            bookLent.setDateReturned(LocalDateTime.now());
            bookLentService.save(bookLent);
        }
        model.addAttribute("referer", request.getHeader("referer"));
        return "redirect:/reader/list";

    }
}
