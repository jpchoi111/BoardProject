package com.mycorp.boardproject.board.dto;

import com.mycorp.boardproject.board.domain.Board;
import lombok.*;

/**
 * DTO for returning board post data to the client.
 * Used in GET /boards or after POST/PUT responses.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder  // Enables clean object creation from entity
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private String authorName;
    private String createdAt;
    private String updatedAt;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.authorName = board.getAuthorName();
    }

    public static BoardResponseDto fromEntity(Board board) {
        BoardResponseDto dto = new BoardResponseDto();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setAuthorName(board.getAuthorName());
        return dto;
    }

}

