package io;

import models.Property;
import models.Reading;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DAOReadings {
    //PROPERTIES
    Set<Reading> readings;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd'T'HH:mm:ss");
    //CONSTRUCTOR
    public DAOReadings() throws IOException {
        this.readings = new HashSet<>(loadData());
    }
    //METHODS
    /**
     * This method reads a file and create a list of that file.
     * @return List.
     * @throws IOException
     */
    public List<Reading> loadData() throws IOException {
        //File to read with readings
        Path file = Paths.get("src","resources","readings.csv");
        return Files.lines(file)
                .map( str -> {
                    String[] cad = str.split(",");

                    //Create object Reading
                    try {
                        return new Reading(Long.parseLong(cad[0]),Double.parseDouble(cad[1]),Double.parseDouble(cad[2]),
                                LocalDateTime.parse(cad[3],formatter),
                                new DAOProperty().findById(Integer.parseInt(cad[4])));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    /**
     * This method saves the current file.
     * @throws IOException
     */
    public void saveData() throws IOException {
        //Open the file readings.csv
        Path file = Paths.get("src", "resources", "readings.csv");
        BufferedWriter bw = Files.newBufferedWriter(file,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE); //* to rewrite the file

        //For each reading generate a string in a line
        for(Reading reading : readings) {
            StringBuffer sb = new StringBuffer();
            sb.append(reading.getId()).append(",");
            sb.append(reading.getTemperature()).append(",");
            sb.append(reading.getHumidity()).append(",");
            sb.append(reading.getMoment()).append(",");
            sb.append(reading.getProperty());

            //Write line in file readings.csv
            bw.write(sb.toString());
            bw.newLine();
        }

        //Close the BufferedWriter
        bw.close();
    }

    /**
     * This method adds and Reading object to Set.
     * @param reading Reading object.
     */
    public void addReading(Reading reading){
        this.readings.add(reading);
    }

    /**
     * This method deletes a Reading object from a Set.
     * @param reading Reading object.
     */
    public void deleteReading(Reading reading){
        this.readings.remove(reading);
    }

}
