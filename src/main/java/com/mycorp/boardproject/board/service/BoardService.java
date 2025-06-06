package com.mycorp.boardproject.board.service;

import com.mycorp.boardproject.board.domain.Board;
import com.mycorp.boardproject.board.dto.BoardRequestDto;
import com.mycorp.boardproject.board.dto.BoardResponseDto;
import com.mycorp.boardproject.board.dto.BoardUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

public interface BoardService {
    /**
     * Create a new board post by a specific member.
     * @param requestDto DTO containing title, content, etc.
     * @return ID of the created board post
     */
    Long createBoard(BoardRequestDto requestDto);

    /**
     * Update an existing board post.
     *
     * @param id          ID of the board post to update
     * @param requestDto  DTO containing new title and content
     * @return updated board post as a response DTO
     */
    BoardResponseDto updateBoard(Long id, BoardUpdateRequestDto requestDto);

    /**
     * Get a single board post by ID.
     *
     * @param id  ID of the board post
     * @return board entity
     */
    Board getBoard(Long id);

    /**
     * Retrieve a board post by its ID and return it as a response DTO.
     *
     * @param id ID of the board post to retrieve
     * @return the board post as a response DTO
     * @throws ResponseStatusException if the board is not found
     */
    BoardResponseDto getBoardById(Long id);

    /**
     * Get a paginated list of board posts.
     *
     * @param pageable  pagination and sorting information
     * @return a page of board response DTOs
     */
    Page<BoardResponseDto> getBoards(Pageable pageable);

    /**
     * Delete a board post by ID.
     *
     * @param id  ID of the board post
     * @return true if deleted successfully, false if post not found
     */
    boolean deleteBoard(Long id);
}


