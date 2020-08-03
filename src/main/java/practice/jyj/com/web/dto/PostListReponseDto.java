package practice.jyj.com.web.dto;

import practice.jyj.com.domain.posts.Posts;

import java.time.LocalDateTime;

public class PostListReponseDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public PostListReponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
