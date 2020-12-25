package dev.milzipmoza.tecobrary.core.domain.librarybook.book.dto;

import dev.milzipmoza.tecobrary.core.domain.librarybook.dto.LibraryBookSummaryDto;
import dev.milzipmoza.tecobrary.core.domain.librarybook.entity.LibraryBook;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BookEnrollDto {

    private LibraryBookSummaryDto libraryBook;
    private List<BookDto> books;

    @Builder
    public BookEnrollDto(LibraryBookSummaryDto libraryBook, List<BookDto> books) {
        this.libraryBook = libraryBook;
        this.books = books;
    }

    public static BookEnrollDto of(LibraryBook libraryBook) {
        return BookEnrollDto.builder()
                .libraryBook(LibraryBookSummaryDto.builder()
                        .id(libraryBook.getId())
                        .isbn(libraryBook.getBookInfo().getIsbn())
                        .title(libraryBook.getBookInfo().getTitle())
                        .totalCount(libraryBook.getBooks().size())
                        .build())
                .books(libraryBook.getBooks()
                        .stream()
                        .map(BookDto::of)
                        .collect(Collectors.toList()))
                .build();
    }
}
