package me.islombek.booklibrary.model;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @NotNull
    @Comment(value = "ID книги")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(max = 150, message = "Длина поля Название книги не может быть больше 150 символов")
    @Comment(value = "Название книги")
    private String title;
    @NotNull
    @Size(max = 150, message = "Длина поля Автор книги не может быть больше 150 символов")
    @Comment(value = "Автор книги")
    private String author;
    @Size(max = 150, message = "Длина поля Описание книги не может быть больше 150 символов")
    @Comment(value = "Описание книги")
    private String description;
}
