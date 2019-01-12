package pl.parser.nbp;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;



public class MainClass {

    public static void main(String[] args) {
        Long start = System.nanoTime();

        FileHelper fileHelper = new FileHelper();

        LocalDate dateRangeFrom = LocalDate.parse(args[1]);
        LocalDate dateRangeTo = LocalDate.parse(args[2]);

        Map<Long, List<String>> filesByYear = fileHelper.getFilesToDownload(dateRangeFrom, dateRangeTo);
        XMLHelper xmlHelper = new XMLHelper();

        try {
            xmlHelper.downloadXMLData(filesByYear);
        } catch (IOException e) {}



        System.out.println((double) (System.nanoTime() - start) / 1_000_000_000.0);
    }
}
