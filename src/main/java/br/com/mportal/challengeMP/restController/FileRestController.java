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
import br.com.mportal.challengeMP.config.validate.ValidateCsv;
import br.com.mportal.challengeMP.dto.MetricsDto;
import br.com.mportal.challengeMP.model.Person;
import br.com.mportal.challengeMP.repository.PersonRepository;
import br.com.mportal.challengeMP.service.PersonService;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<MetricsDto> uploadFile(@RequestBody MultipartFile csvFile ) throws IOException, ParseException, NullPointerException {

        CSVParser csvParser = CSVFormat.EXCEL.withHeader().parse(new InputStreamReader(csvFile.getInputStream()));

        List<CSVRecord> csvRecords = csvParser.getRecords();

        ValidateCsv.validateContent(csvRecords);
        ValidateCsv.validateHeader(csvParser.getHeaderNames());

        List<Person> persons = personService.fixDatesAndCreatePersons(csvRecords);
        
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

        //Utilizando o sort by para ter certeza de que o dado virá ordenado, porém quando persistido ele já estava ordenado no banco de dados
        List<Person> persons = personRepository.findAll(Sort.by(Sort.Direction.ASC, "name", "lastName"));
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=csv_teste_download.csv");
        
        personService.writeCsv(response.getWriter(), persons);
    }
}
