package ee.bitweb.testingsample.domain.datapoint.features;

import ee.bitweb.testingsample.common.exception.persistence.EntityNotFoundException;
import ee.bitweb.testingsample.domain.datapoint.DataPointHelper;
import ee.bitweb.testingsample.domain.datapoint.common.DataPoint;
import ee.bitweb.testingsample.domain.datapoint.external.ExternalService;
import ee.bitweb.testingsample.domain.datapoint.external.ExternalServiceApi;
import ee.bitweb.testingsample.domain.datapoint.features.create.CreateDataPointFeature;
import ee.bitweb.testingsample.domain.datapoint.features.update.UpdateDataPointFeature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class ImportDataPointsFeatureUnitTests {

    @InjectMocks
    private UpdateDataPointFeature updateFeature;

    @Mock
    private ImportDataPointsFeature importDataPointsFeature;

    @Mock
    private CreateDataPointFeature createFeature;

    @Mock
    private GetDataPointByIdFeature getDataPointByExternalIdFeature;

    @Mock
    private ExternalService externalService;

//mockida välisliidestuse response
// mokcida getdatapointbyexternalidfeature, et käima läheks updateFeature
//    peate tekitama olukorra, kus vistatakse entitynotfoundexecption
//    min 2 testi


    @Test
    void findAll() {
        DataPoint point = DataPointHelper.create(1L);
        point.setId(1L);
        ExternalServiceApi.DataPointResponse dataPointResponse = new ExternalServiceApi.DataPointResponse();
        dataPointResponse.setComment("some comment");
        dataPointResponse.setSignificance(1);
        dataPointResponse.setExternalId("some id");
        dataPointResponse.setValue("some value");

        List<DataPoint> dataPoint =  new ArrayList<>();
        doReturn(List.of(dataPointResponse)).when(externalService).getAll();
        doReturn(point).when(createFeature).create(any());
        doThrow(EntityNotFoundException.class).when(getDataPointByExternalIdFeature).get(Long.valueOf(anyString()));
        importDataPointsFeature.execute();


    }
}