package dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private String title;
    private String description;
    private LocalDate validity;
    private TypePeriod period;

    DateTimeFormatter formatterBr = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Task(String title, String description, String validity, TypePeriod period) {
        this.title = title;
        this.description = description;
        this.period = period;
        this.validity = LocalDate.parse(validity, formatterBr);
    }

    @Override
    public String toString() {
        return "Título: " + this.title + "\nDescrição: " + this.description +
                "\nData da realização da tarefa: " + this.validity.format(formatterBr) +
                "\nPeríodo da realização da tarefa: período da " + this.period.getDayPeriod() + ".";
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getValidity() {
        return validity;
    }

    public TypePeriod getPeriod() {
        return period;
    }
}

