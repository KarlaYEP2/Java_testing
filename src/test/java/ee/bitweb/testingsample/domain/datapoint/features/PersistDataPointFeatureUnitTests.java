package ee.bitweb.testingsample.domain.datapoint.features;

import ee.bitweb.testingsample.common.exception.persistence.ConflictException;
import ee.bitweb.testingsample.common.exception.persistence.EntityNotFoundException;
import ee.bitweb.testingsample.domain.datapoint.DataPointHelper;
import ee.bitweb.testingsample.domain.datapoint.common.DataPoint;
import ee.bitweb.testingsample.domain.datapoint.common.DataPointRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PersistDataPointFeatureUnitTests {

    @InjectMocks
    private PersistDataPointFeature persistDataPointFeature;

    @Mock
    private DataPointRepository repository;

    @Captor
    private ArgumentCaptor<DataPoint> dataPointArgumentCaptor;

    @Test
    void OnValidDataPointShouldPersistAndSave() throws Exception {
        DataPoint point = DataPointHelper.create(1L);
        persistDataPointFeature.save(point);

    }
    @Test
    void InvalidDataPointShouldReturnError() throws Exception {
        DataPoint point = DataPointHelper.create(1L);
        persistDataPointFeature.save(point);

        when(repository.save(point)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(ConflictException.class,
                ()->{
                    persistDataPointFeature.save(point);
                });

    };
    }



