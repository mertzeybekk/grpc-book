package com.devproblems;

import com.devProblems.Author;
import com.devProblems.Book;
import com.devProblems.BookAuthorServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.stream.Collectors;

@GrpcService
public class BookAuthorService extends BookAuthorServiceGrpc.BookAuthorServiceImplBase {
    @Override
    public void getAuthor(Author request, StreamObserver<Author> responseObserver) {
        TestDb.getAuthorsFromTempDb().
                stream().
                filter(author -> author.getAuthorId() == request.getAuthorId())
                .findFirst().
                ifPresent(responseObserver::onNext);

    }

    @Override
    public void getBooksByAuthor(Author request, StreamObserver<Book> responseObserver) {
        TestDb.getBooksFromTempDb()
                .stream()
                        .filter(book -> book.getAuthorId() == request.getAuthorId())
                                .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<Book> getExpensiveBook(StreamObserver<Book> responseObserver) {
        return super.getExpensiveBook(responseObserver);
    }

    @Override
    public StreamObserver<Book> getBooksByGender(StreamObserver<Book> responseObserver) {
        return super.getBooksByGender(responseObserver);
    }
}
