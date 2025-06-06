package com.mycorp.boardproject.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for creating a new board post.
 * Used in POST /boards endpoint.
 */
@Getter
@Setter
@NoArgsConstructor  // Required for JSON deserialization
public class BoardRequestDto {

    @NotBlank(message = "Title is required.")
    @Size(max = 100, message = "Title can be up to 100 characters.")
    private String title;

    @NotBlank(message = "Content is required.")
    private String content;
}