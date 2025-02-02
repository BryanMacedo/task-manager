package dominio;

import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
    private LocalDate validity;
    private TypePeriod period;

    public Task(String title, String description, LocalDate validity, TypePeriod period) {
        this.title = title;
        this.description = description;
        this.validity = validity;
        this.period = period;
    }

    @Override
    public String toString() {
        return "Título: " + this.title + "\nDescrição: " + this.description +
                "\nData da realização da tarefa: " + this.validity +
                "\nPeríodo da realização da tarefa: período da " + this.period.getDayPeriod();
    }

    public String getTitle() {
        return title;
    }
}

