package practice.jyj.com.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.jyj.com.domain.posts.Posts;
import practice.jyj.com.domain.posts.PostsRepository;
import practice.jyj.com.web.dto.PostListReponseDto;
import practice.jyj.com.web.dto.PostsResponseDto;
import practice.jyj.com.web.dto.PostsSaveRequestDto;
import practice.jyj.com.web.dto.PostsUpdateRequetDto;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequetDto requestDto){
        Posts posts=postsRepository.findById(id).orElseThrow(()-> new
                IllegalArgumentException("해당 게시글이 없습니다.id=" +id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (long id){
        Posts entity =postsRepository.findById(id).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostListReponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostListReponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts=postsRepository.findById(id)
                .orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);
    }
}
