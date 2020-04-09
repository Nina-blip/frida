package be.vdab.frida.repositories;

import be.vdab.frida.domain.Snack;
import be.vdab.frida.exceptions.SnackNietGevondenException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@JdbcTest
@Import(JdbcSnackRepository.class)
@Sql("/insertSnacks.sql")
class JdbcSnackRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String SNACKS = "snacks";
    private final JdbcSnackRepository repository;

    public JdbcSnackRepositoryTest(JdbcSnackRepository repository) {
        this.repository = repository;
    }

    private long idVanDeSnack(){
        return super.jdbcTemplate.queryForObject("select id from snacks where naam='test'", Long.class);
    }

    @Test
    void findById(){
        assertThat(repository.findById(idVanDeSnack()).get().getNaam()).isEqualTo("test");
    }

    @Test
    void findByOnbestaandeIdGeeftLeegResultaat(){
        assertThat(repository.findById(-1)).isEmpty();
    }

    @Test
    void update(){
        Long id = idVanDeSnack();
        Snack snack = new Snack (id, "test", BigDecimal.ONE);
        repository.update(snack);
        assertThat(super.jdbcTemplate.queryForObject("select prijs from snacks where id = ?",
                BigDecimal.class, id)).isOne();
    }

    @Test
    void UpdateMetOnbestaandeIdGeeftEenFout(){
        assertThatExceptionOfType(SnackNietGevondenException.class).isThrownBy(
                ()->repository.update(new Snack(-1, "test", BigDecimal.TEN)));
    }

    @Test
    void findByBeginNaam(){
        String begin = "t";
        assertThat(repository.findByBeginNaam(begin))
                .hasSize(super.countRowsInTableWhere(SNACKS, "naam like '"+ begin + "%'"))
                .extracting(snack -> snack.getNaam().toLowerCase())
                .allSatisfy(naam -> assertThat(naam).startsWith("t"))
                .isSorted();
    }
}