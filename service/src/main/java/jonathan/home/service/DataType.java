package jonathan.home.service;

public class DataType {
    private final String valueA;
    private final String valueB;

    private final double doubleA;

    public DataType(String valueA, String valueB, double doubleA){
        this.valueA = valueA;
        this.valueB = valueB;
        this.doubleA = doubleA;
    }

    public String getValueA() {return valueA;}
    public String getValueB() {return valueB;}
    public double getDoubleA() {return doubleA;}

}
