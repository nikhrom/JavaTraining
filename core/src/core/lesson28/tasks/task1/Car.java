package core.lesson28.tasks.task1;


@Table(scheme = "garage", table =  "car")
public class Car {

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }


    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }



}
