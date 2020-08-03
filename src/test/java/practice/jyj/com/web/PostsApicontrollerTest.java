package practice.jyj.com.web;

import org.junit.After;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import practice.jyj.com.domain.posts.Posts;
import practice.jyj.com.domain.posts.PostsRepository;
import practice.jyj.com.web.dto.PostsSaveRequestDto;
import practice.jyj.com.web.dto.PostsUpdateRequetDto;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApicontrollerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception{
        //given //테스트 기반 환경 구축하는 단계
        String title="title";
        String content="content";

        PostsSaveRequestDto requestDto= PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url="http://localhost:"+port+"/api/v1/posts";

        //when //내가 테스트 하고자 하는것
        ResponseEntity<Long> responseEntity=restTemplate.postForEntity(url,requestDto,Long.class);


        //then //테스트 결과 검증

        assertThat(responseEntity.getStatusCode()).
                isEqualTo(HttpStatus.OK);

        assertThat(responseEntity.getBody()).
                isGreaterThan(0L);

        List<Posts> all= postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    public void Posts_수정된다() throws Exception{
        //given
        Posts savedPosts= postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId=savedPosts.getId();
        String expectedTitle="title2";
        String expectedContent="content2";

        PostsUpdateRequetDto requetDto=
                PostsUpdateRequetDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url="http://localhost:" + port+ "/api/v1/posts/"+updateId;

        HttpEntity<PostsUpdateRequetDto> requestEntity=new
                HttpEntity<>(requetDto);


        //when
        ResponseEntity<Long> responseEntity=restTemplate.
                exchange(url, HttpMethod.PUT, requestEntity,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all =postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }
}