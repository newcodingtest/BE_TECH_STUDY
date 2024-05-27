package com.example.demo.mvc.dto;

import com.example.demo.mvc.entity.Book;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-03T17:31:18+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class BookMapperImpl implements BookMapper {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_11333195168 = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );

    @Override
    public Book bookPostToBook(BookDto.Post book) {
        if ( book == null ) {
            return null;
        }

        Book book1 = new Book();

        if ( book.getCreatedAt() != null ) {
            book1.setCreatedAt( LocalDateTime.parse( book.getCreatedAt(), dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_11333195168 ) );
        }
        if ( book.getLastModifiedAt() != null ) {
            book1.setLastModifiedAt( LocalDateTime.parse( book.getLastModifiedAt(), dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_11333195168 ) );
        }
        book1.setTitleKorean( book.getTitleKorean() );
        book1.setTitleEnglish( book.getTitleEnglish() );
        book1.setDescription( book.getDescription() );
        book1.setAuthor( book.getAuthor() );
        book1.setIsbn( book.getIsbn() );
        book1.setPublishDate( book.getPublishDate() );

        return book1;
    }

    @Override
    public Book bookPatchToBook(BookDto.Patch book) {
        if ( book == null ) {
            return null;
        }

        Book book1 = new Book();

        book1.setTitleKorean( book.getTitleKorean() );
        book1.setTitleEnglish( book.getTitleEnglish() );
        book1.setDescription( book.getDescription() );
        book1.setAuthor( book.getAuthor() );
        book1.setIsbn( book.getIsbn() );
        book1.setPublishDate( book.getPublishDate() );

        return book1;
    }

    @Override
    public BookDto.Response bookToResponse(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto.Response.ResponseBuilder response = BookDto.Response.builder();

        response.titleKorean( book.getTitleKorean() );
        response.titleEnglish( book.getTitleEnglish() );
        response.description( book.getDescription() );
        response.author( book.getAuthor() );
        response.isbn( book.getIsbn() );
        response.publishDate( book.getPublishDate() );

        return response.build();
    }

    @Override
    public List<BookDto.Response> booksToResponse(List<Book> book) {
        if ( book == null ) {
            return null;
        }

        List<BookDto.Response> list = new ArrayList<BookDto.Response>( book.size() );
        for ( Book book1 : book ) {
            list.add( bookToResponse( book1 ) );
        }

        return list;
    }
}
