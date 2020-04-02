package be.vdab.frida.repositories;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.exceptions.SausRepositoryException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("Properties")
public class PropertiesSausRepository implements SausRepository {
    private static final Path PATH = Paths.get("G:/Mijn Drive/Enterprise Java/Spring Fundamentals/data/sauzen.properties");

    @Override
    public List<Saus> findAll() {
        try{
            return Files.lines(PATH)
                    .filter(regel -> !regel.isEmpty())
                    .map(regel -> maakSaus(regel))
                    .collect(Collectors.toList());
        } catch (IOException ex){
            throw new SausRepositoryException("De ingevoerde URL klopt niet: " + PATH);
        }
    }

    private Saus maakSaus(String regel){
        String[] onderdelen = regel.split(",");
        String[] IdEnNaam = onderdelen[0].split(":");
        if (onderdelen.length < 2 || IdEnNaam.length < 1){
            throw new SausRepositoryException("De saus bevat niet alle noodzakelijke eigenschappen");
        }
        onderdelen = Arrays.copyOfRange(onderdelen, 1, onderdelen.length);
        return new Saus(Long.parseLong(IdEnNaam[0]), IdEnNaam[1], onderdelen);
    }
}
