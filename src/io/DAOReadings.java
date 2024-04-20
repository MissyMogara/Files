package io;

import models.Property;
import models.Reading;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.DoubleSupplier;

public class DAOReadings {
    //PROPERTIES
    Set<Reading> readings;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
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
            sb.append(reading.getProperty().getId());

            //Write line in file readings.csv
            bw.write(sb.toString());
            bw.newLine();
        }

        //Close the BufferedWriter
        bw.close();
    }
    //GETTERS AND SETTERS

    public Set<Reading> getReadings() {
        return readings;
    }

    public void setReadings(Set<Reading> readings) {
        this.readings = readings;
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
    //STREAM METHODS

    /**
     * Returns all readings grouping by properties.
     * @return HashMap
     */
    public HashMap<Long,List<Reading>> getReadingsByProperties(){
        HashMap<Long,List<Reading>> readingsByProperties = new HashMap<>();
        this.readings.stream().forEach(read ->{
            readingsByProperties.put(read.getProperty().getId(), this.readings.stream()
                    .filter(read1 -> read1.getProperty().getId().equals(read.getProperty().getId())).toList());

        });
        return readingsByProperties;
    }

    /**
     * Returns max temperature from all reading of that property.
     * @param id Long.
     * @return Double.
     */
    public Double getMaxTemp(Long id){
        Double errorValue = 0D;
        DoubleSupplier supplier = () -> errorValue;
        return this.readings.stream().filter(read -> read.getProperty().getId().equals(id))
                .max(Comparator.comparing(Reading::getTemperature))
                .stream().mapToDouble(Reading::getTemperature)
                .findFirst().orElseGet(supplier);
    }
    /**
     * Returns min temperature from all reading of that property.
     * @param id Long.
     * @return Double.
     */
    public Double getMinTemp(Long id){
        Double errorValue = 0D;
        DoubleSupplier supplier = () -> errorValue;
        return this.readings.stream().filter(read -> read.getProperty().getId().equals(id))
                .max(Comparator.comparing(Reading::getTemperature).reversed())
                .stream().mapToDouble(Reading::getTemperature)
                .findFirst().orElseGet(supplier);
    }

    /**
     * Returns a List of Double of readings' humidity from a property order by date.
     * @param id Long.
     * @return Double List.
     */
    public List<Double> getHumidityByProperty(Long id){
        return this.readings.stream().filter(read -> read.getProperty().getId().equals(id))
                .sorted(Comparator.comparing(Reading::getMoment))
                .map(Reading::getHumidity)
                .toList();
    }

    /**
     * Returns a List od Double of readings' temperatures from a property order by date.
     * @param id Long.
     * @return Double List.
     */
    public List<Double> getTemperatureByProperty(Long id){
        return this.readings.stream().filter(read -> read.getProperty().getId().equals(id))
                .sorted(Comparator.comparing(Reading::getMoment))
                .map(Reading::getTemperature)
                .toList();
    }

    /**
     * Show all readings of a property of a day order by hour.
     * @param id Long.
     * @param dia LocalDate.
     * @return Double List.
     */
    public List<Double> getTemperatureDayByProperty(Long id, LocalDate dia){
    return this.readings.stream().filter(read -> read.getProperty().getId().equals(id))
            .filter(read -> read.getMoment().toLocalDate().equals(dia))
            .sorted((read1,read2) -> read1.getMoment().toLocalTime().compareTo(read2.getMoment().toLocalTime()))
            .map(Reading::getTemperature)
            .toList();
    }

}
