package pl.sda.gdajava25.spring.bilbioteka.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.gdajava25.spring.bilbioteka.model.Book;
import pl.sda.gdajava25.spring.bilbioteka.service.BookService;
import pl.sda.gdajava25.spring.bilbioteka.service.PublishingHouseService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/book/")
public class BookController {
    private final BookService service;
    private final PublishingHouseService phService;

//    @GetMapping("/add/{phId}")
//    public String add(Model model, Book book,
//                      @PathVariable(name = "phId") Long phId) {
//        model.addAttribute("book", book);
//        model.addAttribute("phId", phId);
//
//        return "book-add";
//    }

    @GetMapping("/add")
    public String getForm(Model model, Book book) {
        book.setYearWritten(LocalDate.now().getYear());
        model.addAttribute("publishingHouses", phService.getAllPublishingHouse());
        model.addAttribute("book", book);

        return "book-add";
    }

    @PostMapping("/add")
    public String add(Book book, Long publishingHouseId) {

        service.add(book, publishingHouseId);

        return "redirect:/books/list";
    }

    @GetMapping("/list")
    public String list(Model model,HttpServletRequest request) {
        model.addAttribute("books", service.getAllBook());
        model.addAttribute("referer", request.getHeader("referer"));

        return "book-list";
    }

    @GetMapping("/delete/{bookId}")
    public String delete(Model model,
                         @PathVariable(name = "bookId") Long id) {
        Optional<Book> bookOptional = service.findById(id);
        bookOptional.ifPresent(service::delete);
        return "redirect:/ph/list";

    }

    @GetMapping("/edit/{bookId}")
    public String edit(Model model,
                       @PathVariable(name = "bookId") Long bkID) {
        Optional<Book> phOptional = service.findById(bkID);
        if (phOptional.isPresent()) {
            model.addAttribute("publishingHouses", phService.getAllPublishingHouse());
            model.addAttribute("book", phOptional.get());
            return "book-add";
        }
        return "redirect:/ph/list";
    }

    @GetMapping("/details/{id}")
    public String details(Model model,
                          HttpServletRequest request,
                          @PathVariable(name = "id") Long id) {
        Optional<Book> optionalBook = service.findById(id);
        if (optionalBook.isPresent()) {
            model.addAttribute("book", optionalBook.get());
            model.addAttribute("referer", request.getHeader("referer"));
            return "book-details";
        }
        return "redirect:/book/list";
    }

}
