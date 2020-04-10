package be.vdab.frida.services;

import be.vdab.frida.domain.Snack;
import be.vdab.frida.repositories.SnackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultSnackService implements SnackService {
    private final SnackRepository snackRepository;

    public DefaultSnackService(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    }

    @Override
    public void update(Snack snack){
        snackRepository.update(snack);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Snack> findByBeginNaam(String naam){
        return snackRepository.findByBeginNaam(naam);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Snack> read(long id){
        return snackRepository.findById(id);
    }

}
