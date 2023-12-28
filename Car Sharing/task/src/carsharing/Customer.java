package carsharing;

class Customer extends CarSharingObject {
    Customer(String name) {
        super(name);
    }
    private Integer carId;
    public void setCarId(Integer carId) {
        this.carId = carId;
    }
    public Integer getCarId() {
        return carId;
    }
}
