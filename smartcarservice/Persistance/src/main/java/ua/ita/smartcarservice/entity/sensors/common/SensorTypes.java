package ua.ita.smartcarservice.entity.sensors.common;

public enum SensorTypes {
    SPEED("speed"), // line
    BATTERY("battery"), // doughnut
    FUEL("fuel"), // bar
    MILEAGE("mileage"), // line
    TIRE_PRESSURE("tire pressure"), // line
    OIL_PRESSURE("oil pressure"), // line
    OIL_LEVEL("oil level"); // bar

    private String sensorType;

    private SensorTypes(String sensorType){
        this.sensorType = sensorType;
    }

    public String toString() {
        return sensorType;
    }
}
