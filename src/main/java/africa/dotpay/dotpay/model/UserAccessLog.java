package africa.dotpay.dotpay.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class UserAccessLog implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private LocalDateTime dateTime;
    private String ip;
    private String request;
    private int status;
    private String userAgent;



    public UserAccessLog(LocalDateTime dateTime, String ip, String request, int status, String userAgent) {
        this.dateTime = dateTime;
        this.ip = ip;
        this.request = request;
        this.status = status;
        this.userAgent = userAgent;
    }


        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccessLog that = (UserAccessLog) o;
        return getIp().equals(that.getIp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIp());
    }
}
