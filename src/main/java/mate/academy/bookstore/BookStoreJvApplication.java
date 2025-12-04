package mate.academy.bookstore;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreJvApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreJvApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Book book = new Book();

                book.setTitle("Book 1");
                book.setPrice(BigDecimal.valueOf(1000));
                book.setIsbn("412412");
                book.setAuthor("John Smith");

                bookService.save(book);
                List<Book> books = bookService.findAll();
                System.out.println(books);
            }
        };
    }
}
