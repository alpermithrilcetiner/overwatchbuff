package overwatchbuff.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import overwatchbuff.dto.AbilityDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ability")
@AllArgsConstructor
@NoArgsConstructor
public class Ability implements Serializable {

    public static Ability toAbility(AbilityDTO abilityDTO) {
        return new Ability(abilityDTO.getId(), abilityDTO.getName(), abilityDTO.getDescription(), abilityDTO.is_ultimate(), abilityDTO.getHero().getId());
    }

    @Id
    private int id;

    private String name;

    @Column(name = "description", length = 1024)
    private String description;

    private boolean is_ultimate;

    @Column(name = "hero_id")
    private int heroId;
}
