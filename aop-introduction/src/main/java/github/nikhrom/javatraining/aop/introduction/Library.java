package github.nikhrom.javatraining.aop.introduction;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

@Component("libraryBean")
@RequiredArgsConstructor
public class Library
{
    private final Deque<Book> books = new ArrayDeque<>(List.of(
            new Book("Преступление и наказание"),
            new Book("Мастер и маргарита")
    ));

    public Book getBook(){
        System.out.println("Мы берём книгу " + books.peek());
        return books.pop();
    }


    public void returnBook(Book book){
        System.out.println("Мы возвращаем книгу " + book);
        books.push(book);
    }
}
