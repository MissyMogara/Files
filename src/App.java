import io.DAOProperty;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
        public static void main(String[] args) throws IOException {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd'T'HH:mm:ss");
            LocalDateTime fecha = LocalDateTime.parse("23-08-23T14:45:00", formatter);
            System.out.println(fecha);

            DAOProperty p1 = new DAOProperty();
            System.out.println(p1.getPropertiesBySurface());
            System.out.println(p1.getBiggerProperties());
        }
}
