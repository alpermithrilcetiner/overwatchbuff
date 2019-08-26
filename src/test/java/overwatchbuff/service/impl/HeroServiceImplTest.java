package overwatchbuff.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import overwatchbuff.model.Ability;
import overwatchbuff.model.Hero;
import overwatchbuff.repository.AbilityRepository;
import overwatchbuff.repository.HeroRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HeroServiceImplTest {

    private static Hero hero1 = new Hero();
    private static Hero hero2 = new Hero();

    private static Ability ability1 = new Ability();
    private static Ability ability2 = new Ability();

    private AbilityRepository abilityRepository = mock(AbilityRepository.class);
    private HeroRepository heroRepository = mock(HeroRepository.class);
    private RestTemplate restTemplate = mock(RestTemplate.class);

    private HeroServiceImpl heroService = new HeroServiceImpl(heroRepository, abilityRepository, restTemplate);

    @Before
    public void setUp() {
        when(abilityRepository.findByHeroID(2)).thenReturn(Collections.singletonList(ability2));
        when(abilityRepository.findAll()).thenReturn(Arrays.asList(ability1, ability2));
        when(abilityRepository.findById(1)).thenReturn(Optional.of(ability1));

        when(heroRepository.findAll()).thenReturn(Arrays.asList(hero1, hero2));
        when(heroRepository.findById(1)).thenReturn(Optional.of(hero1));

        hero1.setId(1);
        hero1.setName("Templar Assassin");
        hero1.setReal_name("Lanaya");
        hero1.setArmour(10);
        hero1.setHealth(775);
        hero1.setShield(0);

        hero2.setId(2);
        hero2.setName("Lifestealer");
        hero2.setReal_name("Naix");
        hero2.setArmour(8);
        hero2.setHealth(900);
        hero2.setShield(0);

        heroRepository.save(hero1);
        heroRepository.save(hero2);

        ability1.setId(1);
        ability1.setDescription("Slams the ground, causing tentacles to erupt in all directions, " +
                "damaging and stunning all nearby enemy units.");
        ability1.setHeroId(1);
        ability1.setName("Ravage");
        ability1.set_ultimate(true);

        abilityRepository.save(ability1);

        ability2.setId(2);
        ability2.setDescription("Launches a bloody hook toward a unit or location." +
                " The hook will snag the first unit it encounters, dragging the unit " +
                "back to Pudge and dealing damage if it is an enemy.");
        ability2.setHeroId(2);
        ability2.setName("Meat Hook");
        ability2.set_ultimate(false);
        abilityRepository.save(ability2);

    }
    @Test
    public void find() {
        Optional<Hero> hero = heroService.find(1);
        assertEquals(hero.get(), hero1);
    }

    @Test
    public void findAll() {
        List<Hero> heroes= heroService.findAll();
        assertEquals(2, heroes.size());
    }
}