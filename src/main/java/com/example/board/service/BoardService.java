package com.example.board.service;

import com.example.board.entity.Board;
import com.example.board.exception.ResourceNotFoundException;
import com.example.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Board createPost(@RequestBody Board board) {
        board.setView_cnt(0);   // 다른 방법
        return boardRepository.save(board);
    }

    public Page<Board> ListAllPosts(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public ResponseEntity<Board> getPostById(@PathVariable Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not exist with id :" + id));

        int cnt = board.getView_cnt();
        board.setView_cnt(cnt + 1);
        Board updatedBoard = boardRepository.save(board);   // check

        return ResponseEntity.ok(updatedBoard);
    }

    @Transactional
    public ResponseEntity<Board> updatePost(@PathVariable Long id, @RequestBody Board boardDetails) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not exist with id :" + id));

        board.setTitle(boardDetails.getTitle());
        board.setContent(boardDetails.getContent());

        Board updatedBoard = boardRepository.save(board);
        return ResponseEntity.ok(updatedBoard);
    }

    public ResponseEntity<Map<String, Boolean>> deletePost(@PathVariable Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not exist with id :" + id));

        boardRepository.delete(board);
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
