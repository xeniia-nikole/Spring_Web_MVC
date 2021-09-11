package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;


import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class PostRepository {

  final private Map<Integer, Post> mapPost = new ConcurrentHashMap<>();

  final private AtomicInteger ID = new AtomicInteger(0);

  public Map<Integer, Post> all() {
    return mapPost;
  }

  public Optional<Post> getById(int id) {
    Optional<Post> currentPost = Optional.empty();
    if (mapPost.containsKey(id)) {
    currentPost = Optional.of(mapPost.get(id));
    } else {
      System.out.printf("Постов с ID %d не существует\n", ID.get());
    }
    return currentPost;
  }

  public synchronized Post save(Post post) {
    if (mapPost.containsKey(post.getID()) && post.getIsRemoved()) {
      mapPost.put(post.getID(), post);
    } else if (post.getID() == 0) {
      long newId = ID.incrementAndGet();
      mapPost.put((int) newId, post);
      System.out.printf("Постов с ID %d не существует, добавлен новый пост\n", ID.get());
    }
    return post;
  }

  public void removeById(int id) {
    if (mapPost.containsKey(id)){
      mapPost.get(id).setIsRemoved(true);
      System.out.printf("Пост с ID %d успешно удален\n", ID.get());
    }else {
        System.out.printf("Постов с ID %d не существует\n", ID.get());
    }
  }
}