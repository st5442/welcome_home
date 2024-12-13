package Project3.com.welcome_home.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@NoArgsConstructor
@Component
@SessionScope
public class UserDetails {
    private String userName;
    private String roleID;
    private Integer orderID;
}
