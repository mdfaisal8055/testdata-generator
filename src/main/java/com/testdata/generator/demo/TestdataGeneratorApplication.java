package com.testdata.generator.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestdataGeneratorApplication {


    public static void main(String[] args) {

        SpringApplication.run(TestdataGeneratorApplication.class, args);
        ObjectMapper mapper = new ObjectMapper();
        List<SampleData> sampleData = createSampleData(50);
        String filename = "test-data.json";

        try {
            String sampleDataJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sampleData);
            mapper.writeValue(new File(filename), sampleData);
            System.out.println(sampleDataJson);
            System.out.println("####################################################################");
            System.out.println("Number of successfully generated sample data : " + sampleData.size());
            System.out.println("Successfully created " + filename + " file with test data.");
            System.out.println("####################################################################");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<SampleData> createSampleData(int n) {
        List<SampleData> nData = new ArrayList<SampleData>();
        DataFactory df = new DataFactory();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date minDate = df.getDate(2000, 1, 1);
        Date maxDate = new Date();
        String date = simpleDateFormat.format(df.getDateBetween(minDate, maxDate));
        for (int i = 0; i < n; i++) {
            SampleData sampleData = new SampleData();
            sampleData.setClient_first_name(df.getFirstName());
            sampleData.setClient_last_name(df.getLastName());
            sampleData.setService_description(df.getRandomWord(5) + " " + df.getRandomWord(7) + " " + df.getRandomWord(10));
            sampleData.setService_date(date);
            sampleData.setService_performed_by(df.getName());
            sampleData.setService_amount_paid(Double.valueOf(df.getNumberBetween(100, 1000)));
            sampleData.setService_amount_currency(df.getRandomText(3));
            nData.add(sampleData);
        }
        return nData;
    }

}
