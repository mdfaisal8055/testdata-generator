package com.testdata.generator.demo;

import lombok.Data;

@Data
public class SampleData {

    private String client_first_name;
    private String client_last_name;
    private String service_description;
    private String service_date;
    private String service_performed_by;
    private Double service_amount_paid;
    private String service_amount_currency;

}
