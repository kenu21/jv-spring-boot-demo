package mate.academy.boot.hellobootdemo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    private Double price;

    @NotNull
    private Integer year;
}
