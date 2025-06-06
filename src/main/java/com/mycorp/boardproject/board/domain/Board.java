package com.mycorp.boardproject.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entity representing a Board post.
 *
 * Encapsulates business logic related to the Board within the domain model,
 * promoting rich domain modeling consistent with DDD principles.
 */
@Entity
@Table(name = "boards")
@Getter
@NoArgsConstructor
@ToString(of = {"id", "title", "content", "authorName"})
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Title of the board post
    @Column(nullable = false, length = 100)
    private String title;

    // Content of the board post
    @Lob
    @Column(nullable = false)
    private String content;

    @Lob
    @Column(nullable = true)
    private String authorName;

    /**
     * Constructor to create a Board entity with title and content.
     * Enforces creation rules in one place.
     *
     * @param title   title of the board post
     * @param content content of the board post
     */
    public void createBoard(String title, String content) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content must not be blank");
        }

        this.title = title;
        this.content = content;
    }

    /**
     * Updates the title and content of the board post.
     * Business rules for updating can be enforced here.
     *
     * @param title   new title
     * @param content new content
     */
    public void updateBoard(String title, String content) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content must not be blank");
        }

        this.title = title;
        this.content = content;
    }
}


