package br.com.mportal.challengeMP.service;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import org.apache.commons.math3.util.Precision;

import br.com.mportal.challengeMP.dto.MetricsDto;
import br.com.mportal.challengeMP.model.Person;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class PersonService {

    public List<Person> fixDatesAndCreatePersons(List<CSVRecord> csvRecords) throws ParseException{

        List<Person> persons = new ArrayList<>();

        int currentMonth = LocalDateTime.now().getMonthValue();
        int currentDay = LocalDateTime.now().getDayOfMonth();

        for (CSVRecord csvRecord : csvRecords) {
            int currentYear = LocalDateTime.now().getYear();

            String dateString = "";
            String[] dateSplit = csvRecord.get("Nascimento").split("/");
            int age = Integer.parseInt(csvRecord.get("Idade"));
            int birthMonth = Integer.parseInt(dateSplit[1]);
            int birthDay = Integer.parseInt(dateSplit[0]);

            if(currentMonth < birthMonth){
                currentYear--;
            }else if(currentMonth == birthMonth){
                if(currentDay < birthDay){
                    currentYear--;
                }
            }

            dateSplit[2] = Integer.toString(currentYear - age);


            dateString = dateSplit[0] + "/" + dateSplit[1] + "/" + dateSplit[2];

            Date birthDate =new SimpleDateFormat("dd/MM/yyyy").parse(dateString); 

            persons.add(new Person(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2), csvRecord.get(3), csvRecord.get(4), Integer.parseInt(csvRecord.get(5)), birthDate));
        }

        return persons;
    }

    public List<Person> sortByName(List<Person> persons){

        Collections.sort(persons ,new Comparator() {
            @Override
            public int compare(Object o1, Object o2){
                Person person1 = (Person)o1;
                Person person2 = (Person)o2;

                return person1.getName().compareToIgnoreCase(person2.getName());
            }
        });
        return persons;
    }

    public MetricsDto calculateMetrics(List<Person> persons){

        int quantityWomans = 0;
        int quantityMans = 0;
        int totalPersons = 0;
        double ageTotalAverage = 0;
        double ageWomansAverage = 0;
        double ageMansAverage = 0;

        for (Person person : persons) {
            totalPersons++;
            ageTotalAverage += person.getAge();

            if(person.getGender().equals("Female")){
                quantityWomans++;
                ageWomansAverage += person.getAge();
            }else{
                quantityMans++;
                ageMansAverage += person.getAge();
            }
        }

        ageTotalAverage = Precision.round(ageTotalAverage / totalPersons, 1);
        ageWomansAverage = Precision.round(ageWomansAverage / quantityWomans, 1);
        ageMansAverage = Precision.round(ageMansAverage / quantityMans, 1);

        return new MetricsDto(quantityWomans, quantityMans, ageTotalAverage, ageWomansAverage, ageMansAverage);
    }
    
}
