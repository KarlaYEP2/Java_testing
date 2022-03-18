package ee.bitweb.testingsample.domain.datapoint.features;

import ee.bitweb.testingsample.common.exception.persistence.EntityNotFoundException;
import ee.bitweb.testingsample.domain.datapoint.DataPointHelper;
import ee.bitweb.testingsample.domain.datapoint.common.DataPoint;
import ee.bitweb.testingsample.domain.datapoint.common.DataPointRepository;
import ee.bitweb.testingsample.domain.datapoint.common.DataPointSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class GetDataPointByIdFeatureUnitTests {
    @Mock
    private DataPointRepository repository;

    @InjectMocks
    private GetDataPointByIdFeature getDataPointByIdFeature;

    @Mock
    private EntityNotFoundException entityNotFoundException;

    @Test
    void GetDataPointById() throws Exception {
        DataPoint point = DataPointHelper.create(1L);
        point.setId(1L);

        doReturn(Optional.of(point)).when(repository).findOne(
                DataPointSpecification.id(any())


        );
        getDataPointByIdFeature.get(1L);

        assertEquals("external-id-1", point.getExternalId());
        assertEquals("some-value-1", point.getValue());
        assertEquals("some-comment-1", point.getComment());
        assertEquals(1,point.getSignificance());

    }
    @Test
    void GetDataPointByIdReturnEntityNotFoundException() throws Exception {

        assertThrows(EntityNotFoundException.class, () -> {
            getDataPointByIdFeature.get(1L);
        });

    }

}
