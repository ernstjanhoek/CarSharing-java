package carsharing;

abstract class CarSharingObject {
    protected DBClient client;
    CarSharingObject(String name) {
        this.name = name;
    }
    private String name;
    private Integer id;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public String toString() {
        return this.id + ". " + this.name;
    }
}
