package ru.netology.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  @GetMapping
  public Map<Integer, Post> all() {
    return service.all().
            entrySet().
            stream().
            filter(p -> !p.getValue().getIsRemoved()).
            collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  @GetMapping("/{id}")
  public Post getById(@PathVariable int id) {
    Post post = service.getById(id);
    if (!post.getIsRemoved()) {
      return post;
    } else {
      throw new NotFoundException();
    }
  }

  @PostMapping
  public Post save(@RequestBody Post post) {
    if (!post.getIsRemoved()) {
      return service.save(post);
    } else {
        throw new NotFoundException();
      }
  }

  @DeleteMapping("/{id}")
  public void removeById(@PathVariable int id) {
    service.removeById(id);
  }
}