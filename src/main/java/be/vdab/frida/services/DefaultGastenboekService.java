package be.vdab.frida.services;

import be.vdab.frida.domain.GastenboekEntry;
import be.vdab.frida.repositories.GastenboekRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
class DefaultGastenboekService implements GastenboekService {
    private final GastenboekRepository repository;

    public DefaultGastenboekService(GastenboekRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public long toevoegen(GastenboekEntry entry) {
        return repository.toevoegen(entry);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GastenboekEntry> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.delete(id);
    }
}
