package overwatchbuff.apiresponse;

import overwatchbuff.dto.AbilityDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class OverwatchApiAbilityResponse {

    @JsonProperty
    private int total;

    @JsonProperty
    private List<AbilityDTO> data;
}
