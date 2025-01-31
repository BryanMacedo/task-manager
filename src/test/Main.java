package test;

import dominio.Task;
import service.TaskManager;


import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        TaskManager taskManager = new TaskManager(tasks);
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("1 - Adicionar uma tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Excluir uma tarefa");
            System.out.println("4 - Salvar lista de tarefas");
            System.out.println("5 - Encerrar o programa");
            System.out.print("Escolha: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    sc.nextLine();
                    System.out.print("\nTítulo: ");
                    String title = sc.nextLine();

                    System.out.print("Descrição: ");
                    String description = sc.nextLine();

                    System.out.print("Data de vencimento: (AAAA-MM-DD): ");
                    String stringValidity = sc.nextLine();
                    LocalDate validity = LocalDate.parse(stringValidity);

                    Task task = new Task(title, description, validity);
                    tasks.add(task);
                    taskManager = new TaskManager(tasks);
                    System.out.println("Tarefa adicionada\n");
                }
                case 2 -> {
                    taskManager.listTasks();
                }
                case 3 -> {
                    System.out.println();
                    System.out.println("Listando os títulos das tarefas existentes:");
                    for (Task task : tasks) {
                        System.out.println(task.getTitle());
                    }
                    sc.nextLine();
                    System.out.println("Digite o título da tarefa que deseja excluir");
                    String titleChoice = sc.nextLine();
                    System.out.println();

                    for (Task task : tasks) {
                        if (task.getTitle().equals(titleChoice)) {
                            tasks.remove(task);
                            System.out.println("Tarefa removida com sucesso!\n");
                        } else {
                            System.out.println("Tarefa não encontrada!\n");
                        }
                    }
                }
                case 4 -> {
                    sc.nextLine();
                    System.out.print("Digite o nome que deseja para o arquivo: ");
                    String fileName = sc.nextLine();
                    String path = "C:\\Users\\conta\\Desktop\\TAREFAS\\" + fileName + ".txt";
                    System.out.println();
                    try (FileWriter writer = new FileWriter(path)) {
                        for (Task task : tasks) {
                            writer.write(task + "\n\n");
                        }
                        System.out.println("Tarefa salva");
                    } catch (IOException e) {
                        System.out.println("Erro ao tentar salvar arquivo");
                        e.printStackTrace();
                    }
                }
            }
        }
        sc.close();
    }
}
