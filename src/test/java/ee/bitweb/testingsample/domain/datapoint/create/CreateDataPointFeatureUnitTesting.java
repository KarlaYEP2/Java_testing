package ee.bitweb.testingsample.domain.datapoint.create;

import ee.bitweb.testingsample.domain.datapoint.DataPointHelper;
import ee.bitweb.testingsample.domain.datapoint.common.DataPoint;
import ee.bitweb.testingsample.domain.datapoint.features.create.CreateDataPointFeature;
import ee.bitweb.testingsample.domain.datapoint.features.create.CreateDataPointModel;
import ee.bitweb.testingsample.domain.datapoint.features.update.UpdateDataPointFeature;
import ee.bitweb.testingsample.domain.datapoint.features.update.UpdateDataPointModel;
import io.swagger.models.apideclaration.Model;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Conversions.string;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateDataPointFeatureUnitTesting {

    @InjectMocks
    private CreateDataPointFeature createDataPointFeature;

    @InjectMocks
    private CreateDataPointModel createDataPointModel;

    @InjectMocks
    private UpdateDataPointFeature updateDataPointFeature;

    @Captor
    private ArgumentCaptor<DataPoint> dataPointArgumentCaptor;

    @Captor
    private ArgumentCaptor<UpdateDataPointModel> dataPointModelArgumentCaptor;

    @Test
    void createDataPointFromModel() throws Exception {
        val randomPoint = new CreateDataPointModel(
                "external-id-1",
                "some-value-1",
                "some-comment-1",
                1
        ).toString();
    }
//WIP
}
