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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class GetDataPointByExternalIdFeatureUnitTests {

    @InjectMocks
    private GetDataPointByExternalIdFeature getDataPointByExternalIdFeature;

    @Mock
    private DataPointRepository repository;

    @Test
    void GetDataPointByExternalId() throws Exception {
        DataPoint point = DataPointHelper.create(1L);
        point.setId(1L);

        doReturn(Optional.of(point)).when(repository).findOne(
                DataPointSpecification.externalId(any())

        );
        getDataPointByExternalIdFeature.get("external-id-1");

        assertEquals("external-id-1", point.getExternalId());
        assertEquals("some-value-1", point.getValue());
        assertEquals("some-comment-1", point.getComment());
        assertEquals(1, point.getSignificance());
    }

    @Test
    void GetDataPointByExternalIdReturnEntityNotFoundException() throws Exception {
        assertThrows(EntityNotFoundException.class, () -> {
            getDataPointByExternalIdFeature.get("external-id-1");
        });
    }

    }
