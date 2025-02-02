package dominio;

public enum TypePeriod {
    MANHA("manhã"), TARDE("tarde"), NOITE("noite"), MADRUGADA("madrugada");

    private String dayPeriod;

    TypePeriod(String dayPeriod) {
        this.dayPeriod = dayPeriod;
    }

    public String getDayPeriod() {
        return dayPeriod;
    }
}