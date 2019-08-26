package overwatchbuff.service;

import overwatchbuff.model.Ability;

import java.util.List;
import java.util.Optional;

public interface AbilityService {
    Optional<Ability> find(int id);

    List<Ability> findAll();

    List<Ability> findByHeroId(int heroId);

}
