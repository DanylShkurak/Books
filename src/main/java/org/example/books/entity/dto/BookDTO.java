package org.example.books.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private String title;
    private String price;
    private String availability;
    private String rating;
}
