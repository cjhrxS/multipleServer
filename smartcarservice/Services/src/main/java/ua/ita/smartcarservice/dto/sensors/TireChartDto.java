package ua.ita.smartcarservice.dto.sensors;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class TireChartDto implements IChartDto {

    private Map<String, List<Double>> data;

    private List<String> labels;

    public TireChartDto(Map<String, List<Double>> data, List<String> labels) {
        this.data = data;
        this.labels = labels;
    }

    public int dataSize(){
        return data.values().stream().findFirst().get().size();
    }
}
