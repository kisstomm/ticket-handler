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
@Table(name = "userbankcard")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Userbankcard {

    @Id
    @Column(name = "userbankcard_id", nullable = false)
    private Long userbankcardId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "card_id", nullable = false)
    private String cardId;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "cvc", nullable = false)
    private String cvc;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "currency", nullable = false)
    private String currency;

}
