package entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "PRICES")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
}
