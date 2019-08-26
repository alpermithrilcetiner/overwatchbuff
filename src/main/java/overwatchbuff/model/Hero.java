package overwatchbuff.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "hero")
@RequiredArgsConstructor
public class Hero implements Serializable {

    @Id
    private int id;

    private String name;

    private String real_name;

    private float health;

    private float armour;

    private float shield;
}
