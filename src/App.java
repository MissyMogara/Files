import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
        public static void main(String[] args) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd'T'HH:mm:ss");
            LocalDateTime fecha = LocalDateTime.parse("23-08-23T14:45:00", formatter);
            System.out.println(fecha);
        }
}
