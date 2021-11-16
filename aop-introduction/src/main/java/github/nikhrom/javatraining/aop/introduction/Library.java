package github.nikhrom.javatraining.aop.introduction;

import org.springframework.stereotype.Component;

@Component("libraryBean")
public class Library
{

    public Book getBook(){
        System.out.println("Мы берём книгу");
        return new Book();
    }
}
