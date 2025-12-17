package mate.academy.bookstore.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    @Size(max = 20)
    private String isbn;

    @NotNull
    @DecimalMin(value = "0.01")
    private Double price;

    @Size(max = 2000)
    private String description;

    @Size(max = 2048)
    private String coverImage;
}
