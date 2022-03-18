package ee.bitweb.testingsample.domain.datapoint.features;

import ee.bitweb.testingsample.common.exception.persistence.EntityNotFoundException;
import ee.bitweb.testingsample.domain.datapoint.DataPointHelper;
import ee.bitweb.testingsample.domain.datapoint.common.DataPoint;
import ee.bitweb.testingsample.domain.datapoint.common.DataPointRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class FindAllDataPointFeatureUnitTests {

    @InjectMocks
    private FindAllDataPointsFeature findAllDataPointFeature;

    @Mock
    private DataPointRepository repository;



    @Test
    void onRequestShouldRequestTwoDataPoints() throws Exception {
        DataPoint pointOne = DataPointHelper.create(1L);
        DataPoint pointTwo = DataPointHelper.create(2L);

        when(repository.findAll()).thenReturn(List.of(pointOne,pointTwo));
        List<DataPoint> points = findAllDataPointFeature.find();


      Assertions.assertEquals(2L, points.size());
      Assertions.assertEquals("external-id-1", points.get(0).getExternalId());
      Assertions.assertEquals("some-value-1", points.get(0).getValue());
      Assertions.assertEquals("some-comment-1", points.get(0).getComment());
      Assertions.assertEquals("external-id-2", points.get(1).getExternalId());
      Assertions.assertEquals("some-value-2", points.get(1).getValue());
      Assertions.assertEquals("some-comment-2", points.get(1).getComment());
      }


}
