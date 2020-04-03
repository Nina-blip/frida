package be.vdab.frida.repositories;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.exceptions.SausRepositoryException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Qualifier("CSV")
public class CSVSausRepository implements SausRepository {
    private final Path path ;

    public CSVSausRepository(@Value("${csvSausPath}") Path path) {
        this.path = path;
    }

    @Override
    public List<Saus> findAll() {
        List<Saus> sauzen = new ArrayList<>();
        try {
            return Files.lines(path)
                    .filter(regel -> !regel.isEmpty() )
                    .map(regel -> maakSaus(regel))
                    .collect(Collectors.toList());

        } catch (IOException ex) {
            throw new SausRepositoryException("Fout bij lezen" + path);
        }
    }

    private Saus maakSaus(String regel){
        String[] onderdelen = regel.split(",");
        if (onderdelen.length < 2){
            throw new SausRepositoryException(path + " :" + regel + " bevat minder dan 2 onderdelen");
        }
        try{
            String[] ingredienten = Arrays.copyOfRange(onderdelen, 2, onderdelen.length);
            return new Saus(Long.parseLong(onderdelen[0]), onderdelen[1], ingredienten);
        } catch (NumberFormatException ex){
            throw new SausRepositoryException(path + ":" + regel + "bevat verkeerde id");
        }
    }
}
