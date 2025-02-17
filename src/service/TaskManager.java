package service;

import dominio.Task;
import dominio.TypePeriod;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    boolean isEqual;
    private List<Task> tasks;

    Scanner sc = new Scanner(System.in);
    DateTimeFormatter formatterBr = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask() {
        boolean check = false;
        while (!check) {
            try {
                System.out.print("\nTítulo: ");
                String title = sc.nextLine();

                for (Task task : tasks) {
                    this.isEqual = task.getTitle().equals(title);
                }

                if (!this.isEqual) {
                    System.out.print("Descrição: ");
                    String description = sc.nextLine();

                    System.out.print("Data da realização da tarefa [DD-MM-AAAA]: ");
                    String stringValidity = sc.nextLine();
                    LocalDate validity = LocalDate.parse(stringValidity, formatterBr);

                    System.out.print("Período da realização da tarefa: ");
                    String stringPeriod = sc.nextLine().toUpperCase();

                    String stringPeriodEdited = Normalizer.normalize(stringPeriod, Normalizer.Form.NFD);
                    stringPeriodEdited = stringPeriodEdited.replaceAll("[^\\p{ASCII}]", "");
                    TypePeriod period = TypePeriod.valueOf(stringPeriodEdited);

                    Task task = new Task(title, description, validity, period);
                    tasks.add(task);
                    System.out.println("Tarefa adicionada.\n");
                    check = true;
                } else {
                    System.out.println("\nJá existe uma tarefa com este título, por favor crie uma tarefa com um título diferente.\n");
                }
            } catch (DateTimeParseException e) {
                System.out.println("\nData inválida, por favor informe uma data no formato [DD-MM-AAAA]");
            } catch (IllegalArgumentException e) {
                System.out.println("\nPeríodo inválido, por favor informe um dos seguintes períodos: " +
                        "\nmanhã, tarde, noite ou madrugada.");
            }
        }
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\nNenhuma tarefa existente no momento, por favor crie uma tarefa para poder lista-lá.\n");
        } else {
            System.out.print("\nDeseja filtrar a listagem? ");
            String choice = sc.nextLine();
            choice = choice.toLowerCase();

            if (choice.charAt(0) == 's') {
                int filteredChoice = 0;
                boolean check = false;
                while (!check) {
                    try {
                        System.out.println("Opções de filtragem: ");
                        System.out.println("1 - Filtrar por data.");
                        System.out.println("2 - Filtrar por período.");
                        System.out.print("Escolha: ");
                        filteredChoice = sc.nextInt();
                        if (filteredChoice != 1 && filteredChoice != 2) {
                            throw new InputMismatchException();
                        }
                        check = true;
                    } catch (InputMismatchException e) {
                        System.out.println("\nOpção inválida, por favor informe o número da opção desejada.\n");
                        sc.nextLine();
                    }
                }


                switch (filteredChoice) {
                    case 1 -> {
                        sc.nextLine();
                        boolean checkIntoSwitch = false;
                        while (!checkIntoSwitch) {
                            try {
                                System.out.print("Informe a data que deseja filtrar: ");
                                String stringFilteredDate = sc.nextLine();

                                LocalDate filteredDate = LocalDate.parse(stringFilteredDate, formatterBr);

                                boolean isFound = false;
                                for (Task filteredListTask : tasks) {
                                    if (filteredListTask.getValidity().equals(filteredDate)) {
                                        System.out.println("\n" + filteredListTask);
                                        isFound = true;
                                    }

                                    if (!isFound) {
                                        System.out.println("Não foi encontrado nenhuma tarefa com a data " + filteredDate.format(formatterBr) + ".");
                                    }
                                }
                                checkIntoSwitch = true;
                            } catch (DateTimeParseException e) {
                                System.out.println("\nData inválida, por favor informe uma data no formato [DD-MM-AAAA]\n");
                            }
                        }

                        System.out.println();
                    }
                    case 2 -> {
                        sc.nextLine();
                        boolean checkIntoSwitch = false;
                        while (!checkIntoSwitch) {
                            try {
                                System.out.print("Informe o período que deseja filtrar: ");
                                String stringFilteredPeriod = sc.nextLine().toUpperCase();

                                String stringFilteredPeriodEdited = Normalizer.normalize(stringFilteredPeriod, Normalizer.Form.NFD);
                                stringFilteredPeriodEdited = stringFilteredPeriodEdited.replaceAll("[^\\p{ASCII}]", "");
                                TypePeriod period = TypePeriod.valueOf(stringFilteredPeriodEdited);

                                boolean isFound = false;
                                for (Task filteredListTask : tasks) {
                                    if (filteredListTask.getPeriod().equals(period)) {
                                        System.out.println("\n" + filteredListTask);
                                        isFound = true;
                                    }
                                }

                                if (!isFound) {
                                    System.out.println("Não foi encontrado nenhuma tarefa com o período " + period.getDayPeriod() + ".");
                                }
                                checkIntoSwitch = true;
                            } catch (IllegalArgumentException e) {
                                System.out.println("\nPeríodo inválido, por favor informe um dos seguintes períodos: " +
                                        "\nmanhã, tarde, noite ou madrugada.\n");
                            }
                        }
                        System.out.println();
                    }
                    default -> {
                        System.out.println("\nEscolha invalida.\n");
                    }
                }

            } else {
                for (Task listTask : tasks) {
                    System.out.println("\n" + listTask);
                }
                System.out.println();

            }
        }
    }

    public void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println("\nNenhuma tarefa existente no momento, por favor crie uma tarefa para poder utilizar a opção de remover uma tarefa.\n");
        } else {
            System.out.println("\nListando as tarefas existentes:");
            listTasks();

            sc.nextLine();
            System.out.print("Digite o título da tarefa que deseja excluir: ");
            String titleChoice = sc.nextLine();
            System.out.println();

            if (tasks.removeIf(task -> task.getTitle().equals(titleChoice))) {
                System.out.println("Tarefa removida com sucesso!\n");
            } else {
                System.out.println("Tarefa " + "\"" + titleChoice + "\"" + " não encontrada.\n");
            }
        }
    }

    public void saveTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\nNenhuma tarefa existente no momento, por favor crie uma tarefa para poder salva-lá.\n");
        } else {
            boolean check = false;
            while (!check) {
                System.out.print("\nDigite o nome que deseja para o arquivo: ");
                String fileName = sc.nextLine();
                String path = "C:\\Users\\conta\\Desktop\\TAREFAS\\" + fileName + ".txt";
                System.out.println();
                try (FileWriter writer = new FileWriter(path)) {
                    for (Task task : tasks) {
                        writer.write(task + "\n\n");
                    }
                    System.out.println("Tarefa salva.\n");
                    check = true;
                } catch (FileNotFoundException e) {
                    System.out.println("Não é possível salvar uma tarefa com \\ no nome, por favor informe um nome sem este caractere.");
                } catch (IOException e) {
                    System.out.println("Falha ao tentar salvar arquivo.");
                    e.printStackTrace();
                }
            }
        }
    }

    public void editTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\nNenhuma tarefa existente no momento, por favor crie uma tarefa para poder utilizar a opção de editar uma tarefa.\n");
        } else {
            boolean check = false;
            while (!check) {
                try {
                    System.out.println("\nListando as tarefas existentes:");
                    listTasks();

                    sc.nextLine();
                    System.out.print("Digite o título da tarefa que deseja editar: ");
                    String editTitleChoice = sc.nextLine();

                    for (Task task : tasks) {
                        if (task.getTitle().equals(editTitleChoice)) {
                            System.out.println("\nEscolha qual campo deseja editar: ");
                            System.out.println("1 - Titulo");
                            System.out.println("2 - Descrição");
                            System.out.println("3 - Data");
                            System.out.println("4 - Período");
                            System.out.print("Escolha: ");
                            int editChoice = sc.nextInt();
                            sc.nextLine();

                            switch (editChoice) {
                                case 1 -> {
                                    sc.nextLine();
                                    System.out.print("\nDigite um novo titulo: ");
                                    String newTitle = sc.nextLine();

                                    task.setTitle(newTitle);
                                    check = true;
                                }
                                case 2 -> {
                                    sc.nextLine();
                                    System.out.print("\nDigite uma nova descrição: ");
                                    String newDescription = sc.nextLine();

                                    task.setDescription(newDescription);
                                    check = true;
                                }
                                case 3 -> {
                                    sc.nextLine();
                                    System.out.print("\nDigite uma nova data: ");
                                    String newDateString = sc.nextLine();

                                    LocalDate newDate = LocalDate.parse(newDateString, formatterBr);

                                    task.setValidity(newDate);
                                    check = true;
                                    System.out.println();
                                }
                                case 4 -> {
                                    sc.nextLine();
                                    System.out.print("\nDigite uma novo período: ");
                                    String newPeriodString = sc.nextLine().toUpperCase();

                                    newPeriodString = Normalizer.normalize(newPeriodString, Normalizer.Form.NFD);
                                    newPeriodString = newPeriodString.replaceAll("[^\\p{ASCII}]", "");
                                    TypePeriod newPeriod = TypePeriod.valueOf(newPeriodString);

                                    task.setPeriod(newPeriod);
                                    check = true;
                                    System.out.println();
                                }
                            }
                        }
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("\nData inválida, por favor informe uma data no formato [DD-MM-AAAA]");
                } catch (IllegalArgumentException e) {
                    System.out.println("\nPeríodo inválido, por favor informe um dos seguintes periodos: " +
                            "\nmanhã, tarde, noite ou madrugada.");
                }
            }
        }
    }
}
