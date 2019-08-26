package overwatchbuff.dto;

import overwatchbuff.model.Hero;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class AbilityDTO implements Serializable {

    private int id;

    private String name;

    private String description;

    private boolean is_ultimate;

    private Hero hero;
}
