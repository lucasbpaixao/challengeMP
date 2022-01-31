package br.com.mportal.challengeMP.restController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.unbescape.csv.CsvEscape;

import br.com.mportal.challengeMP.dto.MetricsDto;
import br.com.mportal.challengeMP.model.Person;
import br.com.mportal.challengeMP.repository.PersonRepository;
import br.com.mportal.challengeMP.service.PersonService;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/file")
public class FileRestController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;
    
    @PostMapping("/upload")
    @Transactional
    public ResponseEntity<MetricsDto> uploadFile(@RequestBody MultipartFile csvFile ) throws IOException, ParseException {

        //TODO: mudar lugar de persistencias dos dados e fazer o botão de download do csv

        CSVParser csvParser = CSVFormat.EXCEL.withHeader().parse(new InputStreamReader(csvFile.getInputStream()));

        List<Person> persons = personService.fixDatesAndCreatePersons(csvParser.getRecords());
        
        personService.sortByName(persons);

        MetricsDto metricsDto = personService.calculateMetrics(persons);

        personRepository.deleteAll();

        for (Person person : persons) {
            personRepository.save(person);
        }

        personRepository.flush();

        return ResponseEntity.ok().body(metricsDto);
    }

    @GetMapping("/download/csv_teste_download.csv")
    @Transactional
    public void downloadFile(HttpServletResponse response) throws IOException {

        List<Person> persons = personRepository.findAll();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=csv_teste_download.csv");
        
        personService.writeCsv(response.getWriter(), persons);
    }
}
