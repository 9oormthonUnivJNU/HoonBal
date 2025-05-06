package com.kim.kims.controller;

import com.kim.kims.model.Post;
import com.kim.kims.repository.PostRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Tag(name = "게시판 컨트롤러", description = "HTML 기반 게시판 뷰를 반환합니다")
public class BoardController {

    private final PostRepository postRepository;

    @GetMapping("/")
    @Operation(summary = "메인 페이지", description = "index.html 반환")
    public String index() {
        return "index";
    }

    @GetMapping("/posts")
    @Operation(summary = "게시글 목록", description = "등록된 게시글 리스트를 HTML로 반환")
    public String list(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "list";
    }

    @GetMapping("/posts/new")
    @Operation(summary = "글쓰기 폼", description = "새 글 작성 HTML 폼 반환")
    public String createForm(Model model) {
        model.addAttribute("post", new Post());
        return "create";
    }

    @PostMapping("/posts")
    @Operation(summary = "글 저장", description = "사용자가 작성한 글을 저장하고 목록 페이지로 이동")
    public String save(Post post) {
        postRepository.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}")
    @Operation(summary = "게시글 상세", description = "특정 ID의 게시글 내용을 HTML로 보여줌")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postRepository.findById(id).orElseThrow());
        return "detail";
    }

    @PostMapping("/posts/{id}/delete")
    @Operation(summary = "게시글 삭제", description = "특정 ID의 게시글을 삭제한 후 목록 페이지로 이동")
    public String delete(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "redirect:/posts";
    }
}
