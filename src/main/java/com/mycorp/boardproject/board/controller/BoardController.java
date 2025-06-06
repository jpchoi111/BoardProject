package com.mycorp.boardproject.board.controller;

import com.mycorp.boardproject.board.dto.BoardRequestDto;
import com.mycorp.boardproject.board.dto.BoardResponseDto;
import com.mycorp.boardproject.board.dto.BoardUpdateRequestDto;
import com.mycorp.boardproject.board.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
    this.boardService = boardService;
    }

    /**
     * Create a new board post.
     * @param requestDto Request body containing post details like title and content
     * @return Returns the ID of the newly created post with HTTP 201 (Created) status
     */
    @PostMapping("/")
    public ResponseEntity<Long> createBoard(

            @RequestBody @Valid BoardRequestDto requestDto) {

        Long createdBoardId = boardService.createBoard(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoardId);
    }

    /**
     * Update an existing board post.
     * @param id ID of the post to update (PathVariable)
     * @param requestDto Request body containing updated post data
     * @return Returns the updated post details with HTTP 200 (OK) status
     */
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(
            @PathVariable Long id,
            @RequestBody BoardUpdateRequestDto requestDto) {

        BoardResponseDto updatedBoard = boardService.updateBoard(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBoard);
    }

    /**
     * Retrieve a paginated list of board posts.
     * @param page Page number to retrieve (default is 0)
     * @param size Number of posts per page (default is 10)
     * @return Returns a paginated list of posts with HTTP 200 (OK) status
     */
    @GetMapping
    public ResponseEntity<Page<BoardResponseDto>> getBoards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<BoardResponseDto> boardPage = boardService.getBoards(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(boardPage);
    }

    /**
     * Retrieve a single board post by its ID.
     * @param id ID of the post to retrieve (PathVariable)
     * @return Returns the post data with HTTP 200 (OK) if found, otherwise 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id) {
        BoardResponseDto board = boardService.getBoardById(id);

        if (board == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(board);
    }

    /**
     * Delete a board post by its ID.
     * @param id ID of the post to delete (PathVariable)
     * @return Returns HTTP 204 (No Content) if deletion succeeded, otherwise 404 (Not Found)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boolean isDeleted = boardService.deleteBoard(id);

        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found");
        }

        return ResponseEntity.noContent().build();
    }
}
