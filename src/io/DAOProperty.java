package io;

import models.Property;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DAOProperty {
    //PROPERTIES
    ArrayList<Property> properties;
    //CONSTRUCTOR

    public DAOProperty() throws IOException {
        this.properties = new ArrayList<>(loadData());
    }

    //METHODS

    /**
     * This method reads a file and create a list of that file.
     * @return List.
     * @throws IOException
     */
    public List<Property> loadData() throws IOException {
        //File to read with properties
        Path file = Paths.get("src","resources","properties.csv");
        return Files.lines(file)
                .map( str -> {
                    String[] cad = str.split(",");

                    //Create object Property
                    return new Property(Long.parseLong(cad[0]),cad[1], Double.parseDouble(cad[2]),
                            Double.parseDouble(cad[3]),Double.parseDouble(cad[4]),cad[5],cad[6]);
                })
                .toList();
    }

    /**
     * This method finds and returns a property by id.
     * @param id primitive int.
     * @return Object Property.
     */
    public Property findById(int id){
        return this.properties.stream()
                .filter(property -> property.getId() == (id))
                .findFirst()
                .orElse(new Property());

    }

    /**
     * This method adds a property to an arraylist of properties.
     * @param property Property Object.
     */
    public void addProperty(Property property){
        this.properties.add(property);
    }

    /**
     * This method remoces a property from properties' arraylist.
     * @param property Property Object.
     */
    public void deleteProperty(Property property){
        this.properties.remove(property);
    }

    /**
     * This method returns a property with that name.
     * @param name Property's name.
     * @return Property Object.
     */
    public Property findByName(String name){
        return this.properties.stream()
                .filter(property -> property.getName().equals(name))
                .findFirst()
                .orElse(new Property());
    }
    //STREAMS METHODS

    /**
     * This method returns a List order by surface.
     * @return List of properties.
     */
    public List<Property> getPropertiesBySurface(){
        return this.properties.stream()
                .sorted(Comparator.comparing(Property::getSurface))
                .toList();
    }

    /**
     * This method returns a List with the 3 next bigger properties.
     * @return List of properties.
     */
    public List<Property> getBiggerProperties(){
        return this.properties.stream()
                .sorted(Comparator.comparing(Property::getSurface).reversed())
                .limit(3)
                .toList();
    }


}
