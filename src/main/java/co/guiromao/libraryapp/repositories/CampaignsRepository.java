package co.guiromao.libraryapp.repositories;

import co.guiromao.libraryapp.models.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignsRepository extends JpaRepository<Campaign, Long> {
}
