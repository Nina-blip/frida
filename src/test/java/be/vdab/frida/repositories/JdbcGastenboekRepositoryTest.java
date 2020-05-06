package be.vdab.frida.repositories;

import be.vdab.frida.domain.GastenboekEntry;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@JdbcTest
@Import(JdbcGastenboekRepository.class)
@Sql("/insertEntries.sql")
class JdbcGastenboekRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
private final JdbcGastenboekRepository repository;
private static final String GASTENBOEKENTRIES = "gastenboekentries";

    public JdbcGastenboekRepositoryTest(JdbcGastenboekRepository repository) {
        this.repository = repository;
    }

    @Test
    void findAllgeeftEntriesGesorteerdOpAflopendeDatum(){
        assertThat(repository.findAll()).hasSize(super.countRowsInTable(GASTENBOEKENTRIES))
                .extracting(items -> items.getDatum()).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    void toevoegenMaaktEenGastenBoekEntryCorrectAan(){
        long id = repository.toevoegen(new GastenboekEntry(0, "tester3", "test3"));
        assertThat(super.countRowsInTableWhere(GASTENBOEKENTRIES,"id =" + id)).isOne();
    }

    @Test
    void verwijderenWerktCorrect(){
        long id = repository.toevoegen(new GastenboekEntry(0, "tester3", "test3"));
        repository.delete(id);
        assertThat(super.countRowsInTableWhere(GASTENBOEKENTRIES, "id=" + id)).isZero();
    }
}