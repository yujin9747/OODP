package com.example.demo.domain.request;

import com.example.demo.domain.Book;
import com.example.demo.domain.Library;
import com.example.demo.domain.Role;
import com.example.demo.domain.Student;
import lombok.Getter;

@Getter
public class BookUpdateForm {
    private String title;
    private String isbn;
    private String position;
    private String publisher;

    public BookUpdateForm(String title, String isbn, String position, String publisher) {
        this.title = title;
        this.isbn = isbn;
        this.position = position;
        this.publisher = publisher;
    }

    public interface Builder{
        public BookUpdateForm.Builder title(String title);

        public BookUpdateForm.Builder isbn(String isbn);

        public BookUpdateForm.Builder position(String position);

        public BookUpdateForm.Builder publisher(String publisher);

        public BookUpdateForm build();

    }
    public static class BookUpdateFormBuilder implements BookUpdateForm.Builder {

        private String title;
        private String isbn;
        private String position;
        private String publisher;


        public BookUpdateForm.Builder title(String name) {
            this.title = name;
            return this;
        }

        public BookUpdateForm.Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookUpdateForm.Builder position(String position) {
            this.position = position;
            return this;
        }

        public BookUpdateForm.Builder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }
        public BookUpdateForm build() {
            return new BookUpdateForm(title, isbn, position, publisher);
        }
    }

}
