package kdh.gurujpa.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class BookUuid {

    /**
     * UUID를 그대로 문자형태로 DB에 저장하면 36-byte의 공간을 필요로 한다.
     * 보통 사용하는 bigint의 8-byte에 비해 너무 큰 공간이다.
     *
     * UUID를 사용하되 데이터의 크기를 줄이기 위해 binary를 사용한다. binary를 사용하면 16-byte의 크기로 UUID를 사용할 수 있다.
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARBINARY(16)", nullable = false, updatable = false)
    private UUID id;

    private String title;
    private String isbn;
    private String publisher;


    public BookUuid() {
    }

    public BookUuid(String title, String isbn, String publisher, Long authorId) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    // Entity 의 equals, hashCode 재정의 시 id만을 핵심필드로 설정
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookUuid book = (BookUuid) o;

        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}
