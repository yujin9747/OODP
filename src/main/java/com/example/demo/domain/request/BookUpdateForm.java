package com.example.demo.domain.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookUpdateForm {
    private String title;
    private String isbn;
    private String position;
    private String publisher;

}
