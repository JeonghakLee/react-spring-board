package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public Page<Board> listAllPosts(@PageableDefault(page=0, size=20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.ListAllPosts(pageable);
    }

    @PostMapping("/board")
    public Board createPost(@RequestBody Board board){
        return boardService.createPost(board);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        return boardService.getPostById(id);
    }

    @PutMapping("/board/{id}")
    public ResponseEntity<Board> updatePost(
            @PathVariable Long id, @RequestBody Board boardDetails) {
        return boardService.updatePost(id, boardDetails);
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePost(@PathVariable Long id) {
        return boardService.deletePost(id);
    }
}
