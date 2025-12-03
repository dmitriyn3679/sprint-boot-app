package mate.academy.bookstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false)
    private String author;

    @Setter
    @Column(nullable = false, unique = true, length = 17)
    private String isbn;

    @Setter
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Setter
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @Setter
    @Column(length = 500)
    private String coverImage;
}
