package overwatchbuff.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import overwatchbuff.model.Ability;
import overwatchbuff.repository.AbilityRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbilityServiceImplTest {

    private static Ability ability1 = new Ability();
    private static Ability ability2 = new Ability();

    private AbilityRepository abilityRepository = mock(AbilityRepository.class);

    private AbilityServiceImpl abilityService = new AbilityServiceImpl(abilityRepository);

    @Before
    public void setUp() {
        when(abilityRepository.findByHeroID(2)).thenReturn(Collections.singletonList(ability2));
        when(abilityRepository.findAll()).thenReturn(Arrays.asList(ability1, ability2));
        when(abilityRepository.findById(1)).thenReturn(Optional.of(ability1));

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
    public void findOne() {
        Optional<Ability> ability = abilityService.find(1);
        assertEquals(ability.get(), ability1);
    }

    @Test
    public void findAll() {
        List<Ability> abilityList = abilityService.findAll();
        assertEquals(2, abilityList.size());
    }

    @Test
    public void findByHeroId() {
        List<Ability> abilityList = abilityService.findByHeroId(2);
        assertEquals(1, abilityList.size());
        assertEquals(ability2, abilityList.get(0));
    }
}