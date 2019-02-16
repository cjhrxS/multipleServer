package ua.ita.smartcarservice.service.impl.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.sensors.ARecordDto;
import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.entity.sensors.ISensorEntity;
import ua.ita.smartcarservice.entity.sensors.common.SensorEntityFactory;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.sensors.SensorRepository;
import ua.ita.smartcarservice.repository.sensors.SensorRepositoryFactory;
import ua.ita.smartcarservice.service.sensors.SensorService;

import java.util.ArrayList;
import java.util.List;

@Service(value="basic")
public class SensorServiceImpl implements SensorService {

    @Autowired
    SensorRepositoryFactory repositoryFactory;

    @Autowired
    SensorEntityFactory entityFactory;

    @Autowired
    CarRepository carRepository;


    /* CREATE */

    @Override
    public void addRecord(ARecordDto recordDto) {
        SensorRepository rep = repositoryFactory.getRepository(recordDto.getSensorType());
        rep.save(recordDtoToEntity((RecordDto)recordDto));
    }

    private ISensorEntity recordDtoToEntity(RecordDto recordDto){
        String repositoryType = recordDto.getSensorType();

        ISensorEntity entity = entityFactory.getEntity(repositoryType);
        entity.setCar(carRepository.findByVin(recordDto.getCarVin()));
        entity.setValue(recordDto.getValue());
        if(recordDto.getDate() != null)
            entity.setDate(parseDateToLocal(recordDto.getDate()));

        return entity;
    }


    /* READ */

    private List<Double> getData(List<ISensorEntity> records){
        List<Double> data = new ArrayList<>();
        records.forEach( (record) -> data.add(record.getValue()) );
        return data;
    }

    private List<Double> getDataFromObjArray(List<Object[]> records){
        List<Double> data = new ArrayList<>();
        records.forEach( (record) -> data.add( (double)record[1]) );
        return data;
    }

    private SensorRepository getRepository(DateForChartDto dateForChartDto){
        return repositoryFactory.getRepository(dateForChartDto.getSensorType());
    }

    @Override
    public ChartDto getAllByDay(DateForChartDto dateForChartDto) {
        ParamsProvider params = getParams(dateForChartDto);
        List<ISensorEntity> records = getRepository(dateForChartDto).getAllByDay(params.getDate(), params.getCarId());

        List<Double> data = getData(records);
        List<String> labels = new LabelsProvider().getHours(records);
        return new ChartDto(data, labels);
    }

    @Override
    public ChartDto getAvgByMonth(DateForChartDto dateForChartDto) {
        ParamsProvider params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getAvgByMonth(params.getDate(), params.getCarId());

        return chartDtoForMonths(records);
    }

    @Override
    public ChartDto getMaxByMonth(DateForChartDto dateForChartDto) {
        ParamsProvider params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getMaxByMonth(params.getDate(), params.getCarId());

        return chartDtoForMonths(records);
    }

    @Override
    public ChartDto getMinByMonth(DateForChartDto dateForChartDto) {
        ParamsProvider params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getMinByMonth(params.getDate(), params.getCarId());

        return chartDtoForMonths(records);
    }

    private ChartDto chartDtoForMonths(List<Object[]> records){
        List<Double> data = getDataFromObjArray(records);
        List<String> labels = new LabelsProvider().getDays(records);
        return new ChartDto(data, labels);
    }

    @Override
    public ChartDto getAvgByYear(DateForChartDto dateForChartDto) {
        ParamsProvider params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getAvgByYear(params.getDate(), params.getCarId());

        return chartDtoForYears(records);
    }

    @Override
    public ChartDto getMaxByYear(DateForChartDto dateForChartDto) {
        ParamsProvider  params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getMaxByYear(params.getDate(), params.getCarId());

        return chartDtoForYears(records);
    }

    @Override
    public ChartDto getMinByYear(DateForChartDto dateForChartDto) {
        ParamsProvider  params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getMinByYear(params.getDate(), params.getCarId());

        return chartDtoForYears(records);
    }

    private ChartDto chartDtoForYears(List<Object[]> records){
        List<Double> data = getDataFromObjArray(records);
        List<String> labels = new LabelsProvider().getMonths(records);
        return new ChartDto(data, labels);
    }
}
