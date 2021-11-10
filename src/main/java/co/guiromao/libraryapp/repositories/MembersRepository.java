package co.guiromao.libraryapp.repositories;

import co.guiromao.libraryapp.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<Member, Long> {
}
