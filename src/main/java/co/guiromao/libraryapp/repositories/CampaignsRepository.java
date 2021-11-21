package co.guiromao.libraryapp.repositories;

import co.guiromao.libraryapp.models.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampaignsRepository extends JpaRepository<Campaign, Long> {

    @Query("SELECT c FROM Campaign c WHERE c.name = ?1")
    List<Campaign> findByName(String campaignName);

}
