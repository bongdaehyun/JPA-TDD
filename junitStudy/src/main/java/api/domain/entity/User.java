package api.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userNumber;

    String name;
    String password;

    public User toEntity(){
        return User.builder().name(name).password(password).build();
    }

    @Builder
    public User(Long userNumber, String name, String password){
        Assert.notNull(name,"이름은 필수 입력입니다.");
        Assert.notNull(password,"비밀번호는 필수 입력입니다.");

        this.userNumber=userNumber;
        this.name=name;
        this.password=password;
    }
}
