package dk.serik.recipes.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dk.serik.recipes.Session;
import dk.serik.recipes.entity.UnitEntity;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class UnitJpaRepositoryTest2 {

	@Autowired
	private UnitJpaRepository2 repository;
	
	@MockBean
	private Session session;
	
	@BeforeEach
	public void setupTest() {
		Mockito.when(session.getUserName()).thenReturn("Jens");
	}
	
	@Test
	public void givenExistFourUnits_WhenFetchingAll_ThenFourIsReturned() {
		List<UnitEntity> unitEntities = repository.findAll();
		Assertions.assertFalse(unitEntities.isEmpty());
		Assertions.assertEquals(4, unitEntities.size());
		
	}
	
	@Test
	public void givenExistUnitDeciliter_WhenLookupUnitByAbbreviationDecil_ThenOk() {
		Optional<UnitEntity> opUnit = repository.findByDescriptionContains("Decil");
		Assertions.assertTrue(opUnit.isPresent());
		Assertions.assertEquals("dl", opUnit.get().getLabel());
		Assertions.assertEquals("Deciliter", opUnit.get().getDescription());
	}
	
	@Test
	public void givenExistUnitThe_TableSpoon_WhenLookupUnitByDescription_ske_ThenTwoIsReturned() {
		Optional<List<UnitEntity>> opUnitList = repository.findAllByDescriptionContains("ske");
		Assertions.assertTrue(opUnitList.isPresent());
		Assertions.assertEquals(2, opUnitList.get().size());		
	}
	
	
	@Test
	public void givenValidUnitEntity_WhenSavedToDatabase_ThenOk() {
		UnitEntity saveAndFlush = repository.saveAndFlush(buildUnitEntity());
		List<UnitEntity> unitEntities = repository.findAll();
		Assertions.assertFalse(unitEntities.isEmpty());
		Assertions.assertEquals(5, unitEntities.size());		
		Assertions.assertEquals("Jens", saveAndFlush.getCreatedBy());
		Assertions.assertEquals("Knivspids", saveAndFlush.getDescription());		
	}
	
	
	@Test
	public void givenExistingUnitEntity_WhenDeleted_ThenOk() {
		Optional<UnitEntity> opUnit = repository.findByDescriptionContains("Gram");
		Assertions.assertTrue(opUnit.isPresent());
		Assertions.assertEquals("gr", opUnit.get().getLabel());
		
		repository.delete(opUnit.get());
		opUnit = repository.findByDescriptionContains("Gram");
		Assertions.assertFalse(opUnit.isPresent());
	}
	
	
	
	private UnitEntity buildUnitEntity() {
		UnitEntity unit = new UnitEntity();
		unit.setLabel("knvsps");
		unit.setDescription("Knivspids");
		return unit;
	}
}
