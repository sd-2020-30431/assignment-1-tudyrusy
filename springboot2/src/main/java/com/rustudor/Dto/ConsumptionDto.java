package com.rustudor.Dto;

import java.sql.Timestamp;

public class ConsumptionDto {
    private String name;
    private Timestamp consumptionDate;

    @Override
    public String toString() {
        return "ConsumptionDto{" +
                "name='" + name + '\'' +
                ", consumptionDate=" + consumptionDate +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(Timestamp consumptionDate) {
        this.consumptionDate = consumptionDate;
    }
}
