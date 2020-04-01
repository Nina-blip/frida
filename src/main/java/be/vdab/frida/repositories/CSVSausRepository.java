package be.vdab.frida.repositories;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.exceptions.SausRepositoryException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CSVSausRepository implements SausRepository {
    private static final Path PATH = Paths.get("G:/Mijn Drive/Enterprise Java/Spring Fundamentals/data/sauzen.csv");

    @Override
    public List<Saus> findAll() {
        List<Saus> sauzen = new ArrayList<>();
        try {
            return Files.lines(PATH)
                    .filter(regel -> !regel.isEmpty() )
                    .map(regel -> maakSaus(regel))
                    .collect(Collectors.toList());

        } catch (IOException ex) {
            throw new SausRepositoryException("Fout bij lezen" + PATH);
        }
    }

    private Saus maakSaus(String regel){
        String[] onderdelen = regel.split(",");
        if (onderdelen.length < 2){
            throw new SausRepositoryException(PATH + " :" + regel + " bevat minder dan 2 onderdelen");
        }
        try{
            String[] ingredienten = Arrays.copyOfRange(onderdelen, 2, onderdelen.length);
            return new Saus(Long.parseLong(onderdelen[0]), onderdelen[1], ingredienten);
        } catch (NumberFormatException ex){
            throw new SausRepositoryException(PATH + ":" + regel + "bevat verkeerde id");
        }
    }
}
