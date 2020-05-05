package be.vdab.frida.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class GastenboekEntryTest {
    private GastenboekEntry entries;
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();


    @BeforeEach
    void beforeEach() {
        entries = new GastenboekEntry(0, "tester", "test");
    }

    @Test
    @DisplayName("Naam moet ingevuld zijn")
    void naamMoetIngevuldZijn() {
        assertThat(entries.getNaam()).isEqualTo("tester");
    }

    @Test
    @DisplayName("Boodschap moet ingevuld zijn")
    void boodschapMoetIngevuldZijn() {
        assertThat(entries.getBoodschap()).isEqualTo("test");
    }

    @Test
    @DisplayName("getDatumAlsString moet geldige String bevatten die overeenkomt met huidige dag")
    void getDatumAlsStringMoetGeldigeStringBevattenDieOvereenkomtMetHuidigeDag() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String vandaag = formatter.format(LocalDate.now());
        assertThat(entries.getDatumAlsString()).isEqualTo(vandaag);
    }

    @Test
    void getDatumMoetGeldigeDatumGevenDieOvereenkomtMetHuidigeDag(){
        assertThat(entries.getDatum()).isEqualTo(LocalDate.now());
    }

    @Test
    @DisplayName("naam mag geen null bevatten")
    void naamMagGeenNullBevatten() {
        GastenboekEntry entry =  new GastenboekEntry(0, null, "test");
        Set<ConstraintViolation<GastenboekEntry>> constraintViolations = validator.validate(entry);
        assertEquals(1, constraintViolations.size());
        assertEquals("moet meer dan enkel spaties bevatten", constraintViolations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("Boodschap mag geen null bevatten")
    void boodschapMagGeenNullBevatten() {
        GastenboekEntry entry =  new GastenboekEntry(0, "tester", null);
        Set<ConstraintViolation<GastenboekEntry>> constraintViolations = validator.validate(entry);
        assertEquals(1, constraintViolations.size());
        assertEquals("moet meer dan enkel spaties bevatten", constraintViolations.iterator().next().getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    @DisplayName("naam mag geen lege String zijn of enkel spaties")
    void naamMagGeenLegeStringZijnOfEnkelSpaties(String spaties) {
        GastenboekEntry entry =  new GastenboekEntry(0, spaties, "test");
        Set<ConstraintViolation<GastenboekEntry>> constraintViolations = validator.validate(entry);
        assertEquals(1, constraintViolations.size());
        assertEquals("moet meer dan enkel spaties bevatten", constraintViolations.iterator().next().getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    @DisplayName("Boodschap mag geen lege String zijn of enkel spaties")
    void boodschapMagGeenLegeStringZijnOfEnkelSpaties(String spaties){
        GastenboekEntry entry =  new GastenboekEntry(0, "tester", spaties);
        Set<ConstraintViolation<GastenboekEntry>> constraintViolations = validator.validate(entry);
        assertEquals(1, constraintViolations.size());
        assertEquals("moet meer dan enkel spaties bevatten", constraintViolations.iterator().next().getMessage());
    }


}