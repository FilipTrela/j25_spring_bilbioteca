package pl.sda.gdajava25.spring.bilbioteka.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.gdajava25.spring.bilbioteka.model.Book;
import pl.sda.gdajava25.spring.bilbioteka.model.BookLent;
import pl.sda.gdajava25.spring.bilbioteka.model.Reader;
import pl.sda.gdajava25.spring.bilbioteka.service.ReaderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/reader/")
public class ReaderController {
    private final ReaderService readerService;

    @GetMapping("/add")
    public String add(Model model, Reader reader) {
        model.addAttribute("reader", reader);
        return "reader-add";
    }

    @PostMapping("/add")
    public String add(Reader reader) {
        readerService.add(reader);
        return "redirect:/reader/list";
    }

    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute("readers", readerService.getAllReader());
        model.addAttribute("referer", request.getHeader("referer"));

        return "reader-list";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(name = "id") Long id) {
        readerService.deleteById(id);
        return "redirect:/reader/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model,
                       @PathVariable(name = "id") Long bkID) {
        Optional<Reader> optionalReader = readerService.findById(bkID);
        if (optionalReader.isPresent()) {
            model.addAttribute("reader", optionalReader.get());

            return "reader-add";
        }
        return "redirect:/reader/list";
    }

    @GetMapping("/blList/{id}")
    public String listBookNotReturned(Model model, @PathVariable(name = "id") Long id
    ,HttpServletRequest request) {
        model.addAttribute("referer", request.getHeader("referer"));
        Optional<Reader> byId = readerService.findById(id);
        if (byId.isPresent()) {
            Set<BookLent> bookLents = byId.get().getLents().stream()
                    .filter(bookLent -> bookLent.getDateReturned() == null)
                    .collect(Collectors.toSet());
            model.addAttribute("bookLents", bookLents);
            return "bookLent-list";
        }
        return "redirect:/reader/blList" + id;
    }
    @GetMapping("/details/{id}")
    public String details(Model model,
                          HttpServletRequest request,
                          @PathVariable(name = "id") Long id) {
        Optional<Reader> optionalReader = readerService.findById(id);
        if (optionalReader.isPresent()) {
            model.addAttribute("reader", optionalReader.get());
            model.addAttribute("referer", request.getHeader("referer"));
            return "reader-details";
        }
        return "redirect:/reader/list";
    }
}
