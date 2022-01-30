package br.com.mportal.challengeMP.dto;

public class MetricsDto {
    
    private int quantityWomans;
    private int quantityMans;
    private double ageTotalAverage;
    private double ageWomansAverage;
    private Double ageMansAverage;

    public MetricsDto(){}

    public MetricsDto(int quantityWomans, int quantityMans, double ageTotalAverage, double ageWomansAverage, double ageMansAverage) {
        this.quantityWomans = quantityWomans;
        this.quantityMans = quantityMans;
        this.ageTotalAverage = ageTotalAverage;
        this.ageWomansAverage = ageWomansAverage;
        this.ageMansAverage = ageMansAverage;
    }

    public int getQuantityWomans() {
        return quantityWomans;
    }

    public int getQuantityMans() {
        return quantityMans;
    }

    public double getAgeTotalAverage() {
        return ageTotalAverage;
    }

    public double getAgeWomansAverage() {
        return ageWomansAverage;
    }

    public Double getAgeMansAverage() {
        return ageMansAverage;
    }

    public void setQuantityWomans(int quantityWomans) {
        this.quantityWomans = quantityWomans;
    }

    public void setQuantityMans(int quantityMans) {
        this.quantityMans = quantityMans;
    }

    public void setAgeTotalAverage(double ageTotalAverage) {
        this.ageTotalAverage = ageTotalAverage;
    }

    public void setAgeWomansAverage(double ageWomansAverage) {
        this.ageWomansAverage = ageWomansAverage;
    }

    public void setAgeMansAverage(Double ageMansAverage) {
        this.ageMansAverage = ageMansAverage;
    }
}
