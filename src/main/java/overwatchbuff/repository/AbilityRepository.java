package overwatchbuff.repository;

import overwatchbuff.model.Ability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbilityRepository extends JpaRepository<Ability, Integer> {

    @Query(nativeQuery = true, value = " SELECT * FROM ability WHERE hero_id = :heroId ")
    List<Ability> findByHeroID(@Param("heroId") int heroId);
}
