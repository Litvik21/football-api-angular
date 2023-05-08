package stracture.football.model;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "commission")
    private BigDecimal commission;
    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
