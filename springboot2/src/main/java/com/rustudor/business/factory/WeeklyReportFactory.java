package com.rustudor.business.factory;

public class WeeklyReportFactory extends AbstractFactory{

    @Override
    public Report getReport(String reportType) {
        if(reportType.equalsIgnoreCase("WEEKLY")){
            return new WeeklyReport();
        }
        return null;
    }
}
