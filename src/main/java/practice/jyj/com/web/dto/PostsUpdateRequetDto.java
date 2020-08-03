package practice.jyj.com.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequetDto {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequetDto(String title,String content){
        this.title=title;
        this.content=content;
    }

}
