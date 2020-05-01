package be.vdab.frida.services;

import be.vdab.frida.domain.GastenboekEntry;

import java.util.List;

public interface GastenboekService {
    void toevoegen(GastenboekEntry entry);
    List<GastenboekEntry> findAll();
}
