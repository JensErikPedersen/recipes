package dk.serik.recipes.repository;

import dk.serik.recipes.bean.Session;
import dk.serik.recipes.model.Recipe;
import dk.serik.recipes.model.Tag;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.when;

@DataJpaTest
@Slf4j
public class TagJpaRepositoryTest {
    @Autowired
    private TagJpaRepository repository;
    @MockBean
    private Session session;

    @BeforeEach
    public void setupTest() {
        when(session.getUserName()).thenReturn("Jens");
    }


    @Test
    @DisplayName("Given six Tags When fetching all Then all six tags are returned")
    public void givenExistSixTags_WhenFetchingAll_ThenSixIsReturned() {
        // When
        List<Tag> tags = repository.findAll();

        //Then
        Assertions.assertThat(tags).isNotEmpty();
        Assertions.assertThat(tags.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("Given Tag with label 'Mexi' exist When fetching Tag by label 'Mexi' Then Tag 'Mexi' is returned")
    public void givenMexiTag_WhenFetchingTagByLabelMexi_ThenMexiIsReturned() {
        // When
        Optional<Tag> tag = repository.findTagByLabel("Mexi");

        // Then
        Assertions.assertThat(tag.isPresent()).isTrue();
        Assertions.assertThat(tag.get().getLabel()).isEqualTo("Mexi");
    }

    @Test
    @DisplayName("Given Tag with label 'TEST' does not exist When fetching Tag by label 'TEST' Then no Tag is returned")
    public void givenNoTestTag_WhenFetchingTagByLabelTest_ThenNoneIsReturned() {
        // When
        Optional<Tag> tag = repository.findTagByLabel("TEST");

        // Then
        Assertions.assertThat(tag.isPresent()).isFalse();
    }

    @Test
    @DisplayName("Given valid Tag When saved to database Then Unit is saved ok")
    public void givenValidTagEntity_WhenSavedToDatabase_ThenOk() {
        // Given
        OffsetDateTime now = OffsetDateTime.now();

        // When
        Tag saveAndFlush = repository.saveAndFlush(buildTag());
        List<Tag> unitEntities = repository.findAll();

        // Then
        Assertions.assertThat(unitEntities).isNotEmpty();
        Assertions.assertThat(unitEntities.size()).isEqualTo(7);
        assertThat(now).isCloseTo(saveAndFlush.getCreated(), within(0, ChronoUnit.SECONDS));
        assertThat("Jens").isEqualTo(saveAndFlush.getCreatedBy());
        assertThat( saveAndFlush.getLabel()).isEqualTo("Italiensk");
    }

    @Test
    @DisplayName("Given existing Tag 'Thai' When deleted from database Then Tag is deleted")
    public void givenExistingTagEntity_WhenDeleted_ThenOk() {
        // Given
        Optional<Tag> tag = repository.findTagByLabel("Thai");
        Assertions.assertThat(tag.isPresent()).isTrue();
        Assertions.assertThat(tag.get().getLabel()).isEqualTo("Thai");

        // When
        repository.delete(tag.get());
        tag = repository.findTagByLabel("Thai");

        // Then
        Assertions.assertThat(tag.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Given existing Tag 'Thai' When updated to 'Thaifood' Then Tag is updated")
    public void givenExistingTagEntity_WhenUpdateLabel_ThenOk() {
        // Given
        Optional<Tag> tag = repository.findTagByLabel("Thai");
        Assertions.assertThat(tag.isPresent()).isTrue();

        // When
        tag.get().setLabel("Thaifood");
        OffsetDateTime now = OffsetDateTime.now();
        Tag saveAndFlush = repository.saveAndFlush(tag.get());

        // Then
        assertThat(now).isCloseTo(saveAndFlush.getUpdated(), within(0, ChronoUnit.SECONDS));
        assertThat("Jens").isEqualTo(saveAndFlush.getUpdatedBy());
        assertThat(saveAndFlush.getLabel()).isEqualTo("Thaifood");
    }

    @Test
    @Sql({"/db/test-data/insert_recipes.sql", "/db/test-data/insert_recipe_tags.sql"})
    @DisplayName("Given three Recipes with tag 'Godt til kaffen', When all Recipes Godt til kaffen' is fetched by Tag name, Then 3 recioes are fetched")
    public void given3RecipesWithTag_WhenAllAreFetchedByTagName_ThenAll3AreFetched() {
        //When
        Optional<Tag> tag = repository.findTagByLabel("Godt til kaffen");

        // Then
        Assertions.assertThat(tag.isPresent()).isTrue();
        Assertions.assertThat(tag.get().getLabel()).isEqualTo("Godt til kaffen");
        Set<Recipe> recipeSet = tag.get().getRecipes();
        assertThat(recipeSet.size()).isEqualTo(3);
    }



    private Tag buildTag() {
        Tag tag = new Tag();
        tag.setLabel("Italiensk");
        tag.setCreatedBy(session.getUserName());
        return tag;
    }
}
