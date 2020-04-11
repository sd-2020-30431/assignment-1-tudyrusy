package com.rustudor.business.factory;

public class MonthlyReportFactory extends AbstractFactory{
    @Override
    public Report getReport(String reportType) {
        if(reportType.equalsIgnoreCase("MONTHLY")){
            return new MonthlyReport();
        }
        return null;
    }
}
