package br.com.mportal.challengeMP.config.validate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

public abstract class ValidateCsv {
    public static void validateHeader(List<String> header) throws IOException{
        List<String> correctHeader = new ArrayList<>();

        correctHeader.add("Nome");
        correctHeader.add("UltimoNome");
        correctHeader.add("Email");
        correctHeader.add("Sexo");
        correctHeader.add("IpAcesso");
        correctHeader.add("Idade");
        correctHeader.add("Nascimento");

        for (String item : header) {
            if(!correctHeader.contains(item)){
                throw new IOException("Erro ao carregar o arquivo, favor tente novamente com um arquivo da extensão .csv e com os campos certos.");
            }
        }

    }

    public static void validateContent(List<CSVRecord> csvRecords){
        if(csvRecords.isEmpty()){
            throw new NullPointerException();
        }
    }
}
