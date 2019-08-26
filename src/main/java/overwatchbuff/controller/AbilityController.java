package overwatchbuff.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import overwatchbuff.model.Ability;
import overwatchbuff.service.AbilityService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/abilities")
public class AbilityController {

    private final Logger LOGGER = LoggerFactory.getLogger(AbilityController.class);

    private final AbilityService abilityService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get all hero abilities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned all hero abilities"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<List<Ability>> getAll() {
        LOGGER.info("Getting abilities");
        try {
            List<Ability> abilities = abilityService.findAll();
            LOGGER.info("Load {} heroes");
            return new ResponseEntity<>(abilities, HttpStatus.OK);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get an ability by ability id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned an ability"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Cannot find ability")
    })
    public ResponseEntity<Ability> loadOne(@PathVariable int id) {
        LOGGER.info("Getting ability by id: ", id);
        try {
            Optional<Ability> ability = abilityService.find(id);
            if (ability.isPresent()) {
                LOGGER.info("Found: {}", ability.get());
                return new ResponseEntity<>(ability.get(), HttpStatus.OK);
            } else {
                LOGGER.info("Not Found:");
                return ResponseEntity.notFound().build();
            }
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
