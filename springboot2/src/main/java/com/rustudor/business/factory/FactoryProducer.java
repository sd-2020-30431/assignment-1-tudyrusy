package com.rustudor.business.factory;

public class FactoryProducer {
    public static AbstractFactory getFactory(String factoryType){
        if(factoryType.equalsIgnoreCase("WEEKLY")){
            return new WeeklyReportFactory();
        }else if(factoryType.equalsIgnoreCase("MONTHLY")){
            return new MonthlyReportFactory();
        }
        return null;
    }
}
