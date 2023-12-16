package peaksoft.appplaza.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "info_users")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class InfoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int passportNumber;
    private String address;
    private int cardNumber;
    private LocalDate localDate;
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;
}
