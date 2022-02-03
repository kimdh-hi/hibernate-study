package kdh.gurujpa.repository;

import kdh.gurujpa.domain.composite.AuthorComposite;
import kdh.gurujpa.domain.composite.NameId;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}
