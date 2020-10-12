package es.upm.miw.apaw_practice.domain.models.car_dealership;

public class CDVehicleCreation {
    private String brand;
    private String type;
    private Boolean unused;
    private Integer year;

    public CDVehicleCreation() {
        // Empty for framework
    }

    public CDVehicleCreation(String brand, String type, Boolean unused, Integer year) {
        this.brand = brand;
        this.type = type;
        this.unused = unused;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getUnused() {
        return unused;
    }

    public void setUnused(Boolean unused) {
        this.unused = unused;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
