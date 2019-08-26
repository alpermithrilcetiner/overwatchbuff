package overwatchbuff.apiresponse;

import overwatchbuff.model.Hero;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class OverwatchApiHeroResponse {

    @JsonProperty
    private int total;

    @JsonProperty
    private List<Hero> data;
}
