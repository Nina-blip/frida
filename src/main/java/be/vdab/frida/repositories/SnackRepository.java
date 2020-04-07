package be.vdab.frida.repositories;

import be.vdab.frida.domain.Snack;
import java.util.Optional;
import java.util.List;


public interface SnackRepository {
    Optional<Snack> findById(long id);
    void update(Snack snack);
    List<Snack> findByBeginNaam(String beginNaam);

}
