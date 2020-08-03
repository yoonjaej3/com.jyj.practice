package practice.jyj.com.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice.jyj.com.service.posts.PostsService;
import practice.jyj.com.web.dto.PostsResponseDto;
import practice.jyj.com.web.dto.PostsSaveRequestDto;
import practice.jyj.com.web.dto.PostsUpdateRequetDto;

@RequiredArgsConstructor
@RestController
public class PostsApicontroller {
    
    private final PostsService postsService;
    
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequetDto requestDto){
        return postsService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id)
    {
        return postsService.findById(id);
    }
}
