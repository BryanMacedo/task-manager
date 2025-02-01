package dominio;

import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
    private LocalDate validity;

    public Task(String title, String description, LocalDate validity) {
        this.title = title;
        this.description = description;
        this.validity = validity;
    }

    @Override
    public String toString() {
        return "Título: " + this.title + "\nDescrição: " + this.description +
                "\nData da realização da tarefa: " + this.validity;
    }

    public String getTitle() {
        return title;
    }
}

