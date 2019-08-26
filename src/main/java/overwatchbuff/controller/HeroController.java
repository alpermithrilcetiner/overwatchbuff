package overwatchbuff.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import overwatchbuff.model.Ability;
import overwatchbuff.model.Hero;
import overwatchbuff.service.AbilityService;
import overwatchbuff.service.HeroService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.OPTIONS;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    private final Logger LOGGER = LoggerFactory.getLogger(HeroController.class);

    private final HeroService heroService;

    private final AbilityService abilityService;

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    @ApiOperation(value = "Save all heroes and abilities from unofficial Overwatch api")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Heroes and abilities successfully saved"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<List<Hero>> saveAll() {
        LOGGER.info("Saving heroes and abilities");
        try {
            heroService.saveHeroesAndAbilities();
            LOGGER.info("Saved heroes and abilities");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get hero by  id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned a hero"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Cannot find hero")
    })
    public ResponseEntity<Hero> loadOne(@PathVariable int id) {
        LOGGER.info("start loadOne user by id: ", id);
        try {
            Optional<Hero> hero = heroService.find(id);
            if (hero.isPresent()) {
                LOGGER.info("Found: {}", hero.get());
                return new ResponseEntity<>(hero.get(), HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get all heroes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned all heroes"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<List<Hero>> getAll() {
        LOGGER.info("Getting heroes");
        try {
            List<Hero> heroes = heroService.findAll();
            LOGGER.info("Load {} heroes", heroes.size());
            return new ResponseEntity<>(heroes, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{heroId}/abilities", method = RequestMethod.GET)
    @ApiOperation(value = "Get hero abilities by hero id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned hero abilities"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<List<Ability>> getHeroAbilities(@PathVariable int heroId) {
        LOGGER.info("Getting hero with id: " + heroId);
        try {
            List<Ability> abilities = abilityService.findByHeroId(heroId);
            return new ResponseEntity<>(abilities, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
