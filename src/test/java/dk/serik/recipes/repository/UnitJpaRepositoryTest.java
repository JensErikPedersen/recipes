package dk.serik.recipes.repository;

import dk.serik.recipes.bean.Session;
import dk.serik.recipes.model.Unit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@ExtendWith(SpringExtension.class)
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
	public void givenExistFourUnits_WhenFetchingAll_ThenFourIsReturned() {
		List<Unit> unitEntities = repository.findAll();
		Assertions.assertFalse(unitEntities.isEmpty());
		Assertions.assertEquals(4, unitEntities.size());
		
	}
	
	@Test
	public void givenExistUnitDeciliter_WhenLookupUnitByAbbreviationDecil_ThenOk() {
		Optional<Unit> opUnit = repository.findByDescriptionContains("Decil");
		Assertions.assertTrue(opUnit.isPresent());
		Assertions.assertEquals("dl", opUnit.get().getLabel());
		Assertions.assertEquals("Deciliter", opUnit.get().getDescription());
	}
	
	@Test
	public void givenExistUnitThe_TableSpoon_WhenLookupUnitByDescription_ske_ThenTwoIsReturned() {
		Optional<List<Unit>> opUnitList = repository.findAllByDescriptionContains("ske");
		Assertions.assertTrue(opUnitList.isPresent());
		Assertions.assertEquals(2, opUnitList.get().size());		
	}
	
	
	@Test
	public void givenValidUnitEntity_WhenSavedToDatabase_ThenOk() {
		OffsetDateTime now = OffsetDateTime.now();
		Unit saveAndFlush = repository.saveAndFlush(buildUnitEntity());
		List<Unit> unitEntities = repository.findAll();
		Assertions.assertFalse(unitEntities.isEmpty());
		Assertions.assertEquals(5, unitEntities.size());
		assertThat(now).isCloseTo(saveAndFlush.getCreated(), within(0, ChronoUnit.SECONDS));
		assertThat("Jens").isEqualTo(saveAndFlush.getCreatedBy());		
		Assertions.assertEquals("Knivspids", saveAndFlush.getDescription());		
	}
	
	
	@Test
	public void givenExistingUnitEntity_WhenDeleted_ThenOk() {
		Optional<Unit> opUnit = repository.findByDescriptionContains("Gram");
		Assertions.assertTrue(opUnit.isPresent());
		Assertions.assertEquals("gr", opUnit.get().getLabel());		
		repository.delete(opUnit.get());
		opUnit = repository.findByDescriptionContains("Gram");
		Assertions.assertTrue(opUnit.isEmpty());
	}
	
	@Test
	public void givenExistingUnitEntity_WhenUpdateDescription_ThenOk() {
		Optional<Unit> opUnit = repository.findByDescriptionContains("Gram");
		Assertions.assertTrue(opUnit.isPresent());
		opUnit.get().setDescription("Kilogram");
		OffsetDateTime now = OffsetDateTime.now();
		Unit saveAndFlush = repository.saveAndFlush(opUnit.get());
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
