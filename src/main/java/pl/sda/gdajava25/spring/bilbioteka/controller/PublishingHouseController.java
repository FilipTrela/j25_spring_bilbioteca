package pl.sda.gdajava25.spring.bilbioteka.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.gdajava25.spring.bilbioteka.model.Book;
import pl.sda.gdajava25.spring.bilbioteka.model.PublishingHouse;
import pl.sda.gdajava25.spring.bilbioteka.service.PublishingHouseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/ph/")
public class PublishingHouseController {
    private final PublishingHouseService service;

    @GetMapping("/add")
    public String add(Model model, PublishingHouse publishingHouse) {
        model.addAttribute("publish", publishingHouse);
        return "publishingHouse-add";
    }

    @PostMapping("/add")
    public String add(PublishingHouse publishingHouse) {
        service.add(publishingHouse);

        return "redirect:/ph/list";
    }

    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request) {
        List<PublishingHouse> publishingHouses = service.getAllPublishingHouse();
        model.addAttribute("referer", request.getHeader("referer"));
        model.addAttribute("publish", publishingHouses);

        return "publishingHouse-list";
    }

    @GetMapping("/delete/{phId}")
    public String delete(Model model,
                         @PathVariable(name = "phId") Long id) {
        Optional<PublishingHouse> studentOptional = service.findById(id);
        studentOptional.ifPresent(service::delete);
        return "redirect:/ph/list";

    }

    @GetMapping("/{phId}/books")
    public String showGrades(Model model, HttpServletRequest request,
                             @PathVariable(name = "phId") Long id) {
        Optional<PublishingHouse> optionalPH = service.findById(id);
        model.addAttribute("referer", request.getHeader("referer"));
        if (optionalPH.isPresent()) {
            Set<Book> books = optionalPH.get().getBooks();
            model.addAttribute("books", books);
            return "book-list";
        }
        return "redirect:/demo/hello";
    }

    @GetMapping("/edit/{phId}")
    public String edit(Model model,
                       @PathVariable(name = "phId") Long phID) {
        Optional<PublishingHouse> phOptional = service.findById(phID);
        if (phOptional.isPresent()) {
            model.addAttribute("publish", phOptional.get());
            return "publishingHouse-add";
        }
        return "redirect:/ph/list";
    }
}
