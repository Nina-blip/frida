package be.vdab.frida.services;

import be.vdab.frida.domain.GastenboekEntry;

import java.util.List;

public interface GastenboekService {
    long toevoegen(GastenboekEntry entry);
    List<GastenboekEntry> findAll();
}
