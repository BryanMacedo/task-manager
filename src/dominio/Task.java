package dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private String title;
    private String description;
    private LocalDate validity;
    private TypePeriod period;

    DateTimeFormatter formatterBr = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Task(String title, String description, LocalDate validity, TypePeriod period) {
        this.title = title;
        this.description = description;
        this.period = period;
        this.validity = validity;
    }

    @Override
    public String toString() {
        return "Título: " + this.title + "\nDescrição: " + this.description +
                "\nData da realização da tarefa: " + this.validity.format(formatterBr)+
                "\nPeríodo da realização da tarefa: período da " + this.period.getDayPeriod() + ".";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getValidity() {
        return validity;
    }

    public void setValidity(LocalDate validity) {
        this.validity = validity;
    }

    public TypePeriod getPeriod() {
        return period;
    }

    public void setPeriod(TypePeriod period) {
        this.period = period;
    }
}

