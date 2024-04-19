import io.DAOProperty;
import io.DAOReadings;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
        public static void main(String[] args) throws IOException {
            //DAOs
            DAOProperty daoProperty = new DAOProperty();
            daoProperty.loadData();

            DAOReadings daoReadings = new DAOReadings();
            daoReadings.loadData();

            //STREAMS PROPERTIES
            System.out.println(daoProperty.getPropertiesBySurface());
            System.out.println("--------------------------------------");
            System.out.println(daoProperty.getBiggerProperties());
            System.out.println("--------------------------------------");
            System.out.println(daoProperty.getPropertiesByCity());
            System.out.println("--------------------------------------");
            System.out.println(daoProperty.getAvarageProperies());
            System.out.println("--------------------------------------");
            System.out.println();

            //STREAMS READINGS
            System.out.println(daoReadings.getReadingsByProperties());
            System.out.println("--------------------------------------");
            System.out.println(daoReadings.getMaxTemp(1l));
            System.out.println("--------------------------------------");
            System.out.println(daoReadings.getMinTemp(1l));
            System.out.println("--------------------------------------");
            System.out.println(daoReadings.getHumidityByProperty(1l));
            System.out.println("--------------------------------------");
            System.out.println(daoReadings.getTemperatureByProperty(1l));
            System.out.println("--------------------------------------");
            System.out.println(daoReadings.getTemperatureDayByProperty(1l, LocalDate.parse("2023-05-22")));



        }
}
