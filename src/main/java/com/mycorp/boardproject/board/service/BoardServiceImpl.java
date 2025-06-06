package com.mycorp.boardproject.board.service;

import com.mycorp.boardproject.board.domain.Board;
import com.mycorp.boardproject.board.dto.BoardRequestDto;
import com.mycorp.boardproject.board.dto.BoardResponseDto;
import com.mycorp.boardproject.board.dto.BoardUpdateRequestDto;
import com.mycorp.boardproject.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Service implementation for Board domain.
 *
 * Handles business logic related to boards, following DDD best practices.
 */
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    /**
     * Creates a new board post.
     *
     * @param requestDto DTO containing title and content
     * @return ID of the newly created board post
     */
    @Override
    @Transactional
    public Long createBoard(BoardRequestDto requestDto) {
        // Save the board entity
        Board board = new Board();
        board.createBoard(requestDto.getTitle(), requestDto.getContent());
        boardRepository.save(board);
        return board.getId();
    }

    /**
     * Updates an existing board post.
     *
     * @param id         ID of the board post to update
     * @param requestDto DTO containing new title and content
     * @return Updated board response DTO
     */
    @Override
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        board.updateBoard(requestDto.getTitle(), requestDto.getContent());

        return new BoardResponseDto(board);
    }

    /**
     * Retrieves a single board post by ID.
     *
     * @param id ID of the board post
     * @return Board entity
     */
    @Override
    @Transactional(readOnly = true)
    public Board getBoard(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
    }

    /**
     * Retrieves a board post by its ID and converts it to a response DTO.
     *
     * This method queries the database for the board entity, and if found,
     * transforms it into a {@link BoardResponseDto}. If the board is not found,
     * a {@link ResponseStatusException} with HTTP 404 is thrown.
     *
     * @param id the ID of the board to retrieve
     * @return the corresponding BoardResponseDto
     * @throws ResponseStatusException if the board does not exist (HTTP 404)
     */
    @Override
    @Transactional(readOnly = true)
    public BoardResponseDto getBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found"));
        return BoardResponseDto.fromEntity(board);
    }

    /**
     * Retrieves a paginated list of board posts.
     *
     * @param pageable pagination information
     * @return Page of BoardResponseDto
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BoardResponseDto> getBoards(Pageable pageable) {
        Page<Board> boardPage = boardRepository.findAll(pageable);
        return boardPage.map(BoardResponseDto::fromEntity);
    }

    /**
     * Deletes a board post by ID.
     *
     * @param id ID of the board post
     * @return true if deleted, false if board not found
     */
    @Override
    @Transactional
    public boolean deleteBoard(Long id) {
        Optional<Board> boardOptional = boardRepository.findById(id);
        if (boardOptional.isEmpty()) {
            return false;
        }
        boardRepository.deleteById(id);
        return true;
    }
}


