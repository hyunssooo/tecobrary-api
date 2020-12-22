package dev.milzipmoza.tecobrary.core.domain.books.library.service;

import dev.milzipmoza.tecobrary.core.domain.books.library.dto.LibraryBookDto;
import dev.milzipmoza.tecobrary.core.domain.books.library.dto.LibraryBookEnrollDto;
import dev.milzipmoza.tecobrary.core.domain.books.library.entity.LibraryBook;
import dev.milzipmoza.tecobrary.core.domain.books.library.exception.LibraryBookAlreadyEnrolledException;
import dev.milzipmoza.tecobrary.core.domain.books.library.repository.LibraryBookRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibraryBookCommandService {

    private final LibraryBookRepository libraryBookRepository;

    public LibraryBookDto enroll(LibraryBookEnrollDto enrollBook) {
        try {
            LibraryBook savedBook = libraryBookRepository.save(enrollBook.toEntity());
            return LibraryBookDto.of(savedBook);
        } catch (ConstraintViolationException e) {
            throw new LibraryBookAlreadyEnrolledException("이미 등록된 도서입니다.");
        }
    }
}
