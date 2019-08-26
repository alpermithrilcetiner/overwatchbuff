package overwatchbuff.service.impl;

import overwatchbuff.model.Ability;
import overwatchbuff.repository.AbilityRepository;
import overwatchbuff.repository.HeroRepository;
import overwatchbuff.service.AbilityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import retrofit2.http.OPTIONS;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AbilityServiceImpl implements AbilityService {

    private final AbilityRepository abilityRepository;

    @Override
    public Optional<Ability> find(int id) {
        return abilityRepository.findById(id);
    }

    @Override
    public List<Ability> findAll() {
        return abilityRepository.findAll();
    }

    @Override
    public List<Ability> findByHeroId(int heroId) {
        return abilityRepository.findByHeroID(heroId);
    }
}
