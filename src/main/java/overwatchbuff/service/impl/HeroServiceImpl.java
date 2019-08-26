package overwatchbuff.service.impl;

import overwatchbuff.apiresponse.OverwatchApiAbilityResponse;
import overwatchbuff.apiresponse.OverwatchApiHeroResponse;
import overwatchbuff.model.Ability;
import overwatchbuff.model.Hero;
import overwatchbuff.repository.AbilityRepository;
import overwatchbuff.repository.HeroRepository;
import overwatchbuff.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;

    private final AbilityRepository abilityRepository;

    private final RestTemplate restTemplate;

    @Override
    public Optional<Hero> find(int id) {
        return heroRepository.findById(id);
    }

    @Override
    public List<Hero> findAll() {
        return heroRepository.findAll();
    }

    @Override
    public void saveHeroesAndAbilities() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "alper-overwatch");

        HttpEntity<OverwatchApiHeroResponse> heroEntity = new HttpEntity<>(headers);

        OverwatchApiHeroResponse overwatchApiHeroResponse = restTemplate.exchange("https://overwatch-api.net/api/v1/hero/", HttpMethod.GET, heroEntity, OverwatchApiHeroResponse.class).getBody();

        HttpEntity<OverwatchApiAbilityResponse> abilityEntity = new HttpEntity<>(headers);

        OverwatchApiAbilityResponse overwatchApiAbilityResponse = restTemplate.exchange("https://overwatch-api.net/api/v1/ability/", HttpMethod.GET, abilityEntity, OverwatchApiAbilityResponse.class).getBody();

        if (overwatchApiAbilityResponse != null) {
            abilityRepository.saveAll(overwatchApiAbilityResponse.getData().stream().map(Ability::toAbility).collect(Collectors.toList()));
        }

        if (overwatchApiHeroResponse != null) {
            heroRepository.saveAll(overwatchApiHeroResponse.getData());
        }
    }
}
