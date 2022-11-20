package dk.serik.recipes.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dk.serik.recipes.Session;
import dk.serik.recipes.entity.RatingEntity;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class RatingJpaRepositotyTest {
	
	@Autowired
	private RatingJpaRepository ratingJpaRepository;
	
	@MockBean
	private Session session;
	
	
	@BeforeEach
	public void initTest() {
		Mockito.when(session.getUserName()).thenReturn("Majken");
	}
	
	@Test
	public void givenRatingsExisting_WhenFetchingOneById_ThenOk() {
		Optional<RatingEntity> opRating = ratingJpaRepository.findById("62149cc5-73e4-45ef-8ac2-4d725815d5cf");
		assertThat(opRating.isPresent());
		assertThat(opRating.get().getRating()).isEqualTo(4);
	}
	
	@Test
	public void givenValidRatingEntity_WhenSaved_ThenOk() {
		OffsetDateTime now = OffsetDateTime.now();
		RatingEntity ratingEntity = ratingJpaRepository.saveAndFlush(buildRatingEntity());
		assertThat(ratingEntity.getCreated()).isCloseTo(now, within(0, ChronoUnit.SECONDS));
		assertThat(ratingEntity.getCreatedBy()).isEqualTo("Majken");
	}
	
	
	@Test
	public void givenInValidRatingEntity_WhenSavedAndExceptionIsThrown_ThenOk() {		
		RatingEntity newRatingEntity = buildRatingEntity();
		newRatingEntity.setRating(5);  // already exist		
		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			ratingJpaRepository.saveAndFlush(newRatingEntity);
		});	
		
		log.info("Exception: {}", exception.getMessage());		
		assertThat(exception.getMessage()).contains("could not execute statement; SQL");
	}
	
	@Test
	public void givenExistingRatingEntity_WhenDeleted_ThenOk() {
		Optional<RatingEntity> opRating = ratingJpaRepository.findById("62149cc5-73e4-45ef-8ac2-4d725815d5cf");
		assertThat(opRating.isPresent());
		
		ratingJpaRepository.delete(opRating.get());
		List<RatingEntity> ratingEntities = ratingJpaRepository.findAll();
		assertThat(ratingEntities).isNotEmpty();
		assertEquals(4, ratingEntities.size());
	}
	
	
	@Test
	public void givenExistingRatingEntity_WhenUpdated_ThenOk() {
		Optional<RatingEntity> opRating = ratingJpaRepository.findById("62149cc5-73e4-45ef-8ac2-4d725815d5cf");
		assertThat(opRating.isPresent());		
		opRating.get().setDescription("Magnifique!");
		OffsetDateTime now = OffsetDateTime.now();
		RatingEntity saveAndFlush = ratingJpaRepository.saveAndFlush(opRating.get());
		assertThat(saveAndFlush.getUpdated()).isCloseTo(now, within(0, ChronoUnit.SECONDS));
		assertThat(saveAndFlush.getUpdatedBy()).isEqualTo("Majken");
		assertThat(saveAndFlush.getDescription()).isEqualTo("Magnifique!");
	}
	
	private RatingEntity buildRatingEntity() {
		RatingEntity ratingEntity = new RatingEntity();
		ratingEntity.setDescription("Helt fænomenal... verdensklasse!!!");
		ratingEntity.setRating(6);
		return ratingEntity;
		
	}

}
