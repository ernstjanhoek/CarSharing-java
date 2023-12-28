package carsharing;

class Car extends CarSharingObject {
    Car(String name) {
        super(name);
    }

    private Integer companyId;

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyId() {
        return companyId;
    }
}
