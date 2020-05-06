package be.vdab.frida.repositories;

import be.vdab.frida.domain.GastenboekEntry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
class JdbcGastenboekRepository implements GastenboekRepository {
    private final JdbcTemplate template;
    private final RowMapper<GastenboekEntry> gastenBoekMapper =
            (result, rowNum) -> new GastenboekEntry(result.getLong("id"),result.getDate("datum").toLocalDate(), result.getString("naam"), result.getString("boodschap"));
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcGastenboekRepository(JdbcTemplate template) {
        this.template = template;
        this.simpleJdbcInsert = new SimpleJdbcInsert(template);
        simpleJdbcInsert.withTableName("gastenboekentries");
        simpleJdbcInsert.usingGeneratedKeyColumns("id");
    }

    @Override
    public long toevoegen(GastenboekEntry entry) {
        Map<String, Object> kolommen = new HashMap<>();
        kolommen.put("datum", entry.getDatum());
        kolommen.put("naam", entry.getNaam());
        kolommen.put("boodschap", entry.getBoodschap());
        Number id = simpleJdbcInsert.executeAndReturnKey(kolommen);
        return id.longValue();
    }

    @Override
    public List<GastenboekEntry> findAll() {
        String sql="select id,datum,naam,boodschap from gastenboekentries order by datum desc, id desc";
        return template.query(sql, gastenBoekMapper);
    }

    @Override
    public void delete(long id) {
        String sql="delete from gastenboekentries where id ="+id;
        template.update(sql);
    }
}
