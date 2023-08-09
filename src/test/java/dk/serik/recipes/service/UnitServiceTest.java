package dk.serik.recipes.service;

import dk.serik.recipes.bean.Session;
import dk.serik.recipes.dto.UnitDTO;
import dk.serik.recipes.mockutil.MockUnitUtil;
import dk.serik.recipes.repository.UnitJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UnitServiceTest {
    @Mock
    private UnitJpaRepository repository;
    @Mock
    private Session session;
    @InjectMocks
    private UnitServiceImpl service;

    @Test
    @DisplayName("Given existing Unit, When fetching Unit by Id, Then Unit is returned")
    public void shouldFetchUnitById() {
        final String unitId = "f7823293-7874-4459-9fb7-6b420a0627fa";
        // Given
        given(repository.findById(UUID.fromString(unitId))).willReturn(Optional.of(MockUnitUtil.mockGram()));

        // When
        Optional<UnitDTO> dto = service.findById(unitId);

        // Then
        then(repository).should(times(1)).findById(UUID.fromString(unitId));
        assertThat(dto.isPresent()).isTrue();
        assertThat(dto.get().getName()).isEqualTo("Gram");
    }
}
