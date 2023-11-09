package com.devproblems.Controller;

import com.devproblems.service.BookAuthorClientService;
import com.google.protobuf.Descriptors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Descriptor;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grpc")
public class BookAuthorController {
    private BookAuthorClientService bookAuthorClientService;

    public BookAuthorController(BookAuthorClientService bookAuthorClientService) {
        this.bookAuthorClientService = bookAuthorClientService;
    }

    @GetMapping("/author/{id}")
    public Map<Descriptors.FieldDescriptor, Object> getAuthor(@PathVariable String id) {
        return this.bookAuthorClientService.getAuthor(Integer.parseInt(id));
    }
    @GetMapping("/book/{authorId}")
    public List<Map<Descriptors.FieldDescriptor, Object>> getBooksAuthor(@PathVariable String authorId) throws InterruptedException {
        return this.bookAuthorClientService.getBooksByAuthor(Integer.parseInt(authorId));

    }
    @GetMapping("/book")
    public Map<String, Map<Descriptors.FieldDescriptor, Object>> getExpensiveBook() throws InterruptedException {
        return bookAuthorClientService.getExpensiveBook();
    }

    @GetMapping("/book/author/{gender}")
    public List<Map<Descriptors.FieldDescriptor, Object>> getBookByGender(@PathVariable String gender) throws InterruptedException {
        return bookAuthorClientService.getBooksByGender(gender);
    }
}
