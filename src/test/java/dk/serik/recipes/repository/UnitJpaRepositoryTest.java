package dk.serik.recipes.repository;

import dk.serik.recipes.bean.Session;
import dk.serik.recipes.model.Unit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DataJpaTest
@Slf4j
public class UnitJpaRepositoryTest {

	@Autowired
	private UnitJpaRepository repository;
	
	@MockBean
	private Session session;
	
	@BeforeEach
	public void setupTest() {
		Mockito.when(session.getUserName()).thenReturn("Jens");
	}
	
	@Test
	@DisplayName("Given four Units When Fetching All Then all Four Units are returned")
	public void givenExistFourUnits_WhenFetchingAll_ThenFourIsReturned() {
		// When
		List<Unit> unitEntities = repository.findAll();

		//Then
		Assertions.assertFalse(unitEntities.isEmpty());
		Assertions.assertEquals(4, unitEntities.size());
		
	}
	
	@Test
	@DisplayName("Given existing Deciliter unit When looking up 'Decil' Then Unit Deciliter is returned")
	public void givenExistUnitDeciliter_WhenLookupUnitByAbbreviationDecil_ThenOk() {
		// When
		Optional<Unit> opUnit = repository.findByDescriptionContains("Decil");

		// Then
		Assertions.assertTrue(opUnit.isPresent());
		Assertions.assertEquals("dl", opUnit.get().getLabel());
		Assertions.assertEquals("Deciliter", opUnit.get().getDescription());
	}
	
	@Test
	@DisplayName("Given existing unit Spiseske When looking up Unit by description 'ske' Then Unit Spiseske is returned")
	public void givenExistUnitThe_TableSpoon_WhenLookupUnitByDescription_ske_ThenTwoIsReturned() {
		Optional<List<Unit>> opUnitList = repository.findAllByDescriptionContains("ske");
		Assertions.assertTrue(opUnitList.isPresent());
		Assertions.assertEquals(2, opUnitList.get().size());		
	}
	
	
	@Test
	@DisplayName("Given valid Unit When saved to database Then Unit is saved ok")
	public void givenValidUnitEntity_WhenSavedToDatabase_ThenOk() {
		// Given
		OffsetDateTime now = OffsetDateTime.now();

		// When
		Unit saveAndFlush = repository.saveAndFlush(buildUnitEntity());

		// Then
		List<Unit> unitEntities = repository.findAll();
		Assertions.assertFalse(unitEntities.isEmpty());
		Assertions.assertEquals(5, unitEntities.size());
		assertThat(now).isCloseTo(saveAndFlush.getCreated(), within(0, ChronoUnit.SECONDS));
		assertThat("Jens").isEqualTo(saveAndFlush.getCreatedBy());		
		Assertions.assertEquals("Knivspids", saveAndFlush.getDescription());		
	}
	
	
	@Test
	@DisplayName("Given existing Unit Gram When deleted from database Then Unit is deleted")
	public void givenExistingUnitEntity_WhenDeleted_ThenOk() {
		// Given
		Optional<Unit> opUnit = repository.findByDescriptionContains("Gram");
		Assertions.assertTrue(opUnit.isPresent());
		Assertions.assertEquals("gr", opUnit.get().getLabel());

		// When
		repository.delete(opUnit.get());
		opUnit = repository.findByDescriptionContains("Gram");

		// Then
		Assertions.assertTrue(opUnit.isEmpty());
	}
	
	@Test
	@DisplayName("Given existing Unit Gram When updated to 'Kilogram' Then Unit is updated")
	public void givenExistingUnitEntity_WhenUpdateDescription_ThenOk() {
		// Given
		Optional<Unit> opUnit = repository.findByDescriptionContains("Gram");
		Assertions.assertTrue(opUnit.isPresent());

		// When
		opUnit.get().setDescription("Kilogram");
		OffsetDateTime now = OffsetDateTime.now();
		Unit saveAndFlush = repository.saveAndFlush(opUnit.get());

		// Then
		assertThat(now).isCloseTo(saveAndFlush.getUpdated(), within(0, ChronoUnit.SECONDS));
		assertThat("Jens").isEqualTo(saveAndFlush.getUpdatedBy());
		assertThat(saveAndFlush.getDescription()).isEqualTo("Kilogram");
	}
	
	
	
	private Unit buildUnitEntity() {
		Unit unit = new Unit();
		unit.setLabel("knvsps");
		unit.setDescription("Knivspids");
		return unit;
	}
}
