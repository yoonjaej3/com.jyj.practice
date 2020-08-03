package practice.jyj.com.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.jyj.com.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id //해당 테이블의 PK필드값
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성 규칙
    private Long id;

    @Column(length=500,nullable=false)//해당 클래스의 필드는 칼럼형태가돰.
    private String title;

    @Column(columnDefinition="TEXT",nullable=false)//해당 클래스의 필드는 칼럼형태가돰.
    private String content;
    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성.
    public Posts(String title,String content,String author) {
        this.title=title;
        this.content=content;
        this.author=author;
    }

    public void update(String title,String content){
        this.title=title;
        this.content=content;
    }
}
