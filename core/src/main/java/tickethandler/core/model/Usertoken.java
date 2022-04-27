package tickethandler.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usertoken")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usertoken {

    @Id
    @Column(name = "usertoken_id", nullable = false)
    private Long usertokenId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "token", nullable = false)
    private String token;


}
