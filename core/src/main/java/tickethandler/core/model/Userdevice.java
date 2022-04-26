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
@Table(name = "userdevice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Userdevice {

    @Id
    @Column(name = "userdevice_id", nullable = false)
    private Long userdeviceId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "device_hash", nullable = false)
    private String deviceHash;


}
