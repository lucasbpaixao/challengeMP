package br.com.mportal.challengeMP.restController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.mportal.challengeMP.dto.MetricsDto;
import br.com.mportal.challengeMP.model.Person;
import br.com.mportal.challengeMP.service.PersonService;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/file")
public class FileRestController {

    @Autowired
    private PersonService personService;
    
    @PostMapping("/upload")
    @Transactional
    public ResponseEntity<MetricsDto> uploadFile(@RequestBody MultipartFile csvFile ) throws IOException, ParseException {

        //TODO: Terminar adaptação para funcionar como uma API, já foi feita a correção das datas, aprimorar tambem o algoritmo das datas para caso a pessoa tenha nascido depois da data atual

        CSVParser csvParser = CSVFormat.EXCEL.withHeader().parse(new InputStreamReader(csvFile.getInputStream()));

        List<Person> persons = personService.fixDatesAndCreatePersons(csvParser.getRecords());
        
        personService.sortByName(persons);

        MetricsDto metricsDto = personService.calculateMetrics(persons);

        return ResponseEntity.ok().body(metricsDto);
    }
    
}
