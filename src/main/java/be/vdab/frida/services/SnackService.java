package be.vdab.frida.services;

import be.vdab.frida.domain.Snack;
import java.util.Optional;

import java.util.List;

public interface SnackService {
    void update(Snack snack);
    List<Snack> findByBeginNaam(String naam);
    Optional<Snack> read(long id);
}
