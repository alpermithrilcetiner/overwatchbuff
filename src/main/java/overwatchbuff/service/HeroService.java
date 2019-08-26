package overwatchbuff.service;

import overwatchbuff.model.Hero;

import java.util.List;
import java.util.Optional;

public interface HeroService {
    Optional<Hero> find(int id);

    List<Hero> findAll();

    void saveHeroesAndAbilities();
}
