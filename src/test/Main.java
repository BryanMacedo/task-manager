package test;

import dominio.Task;
import service.TaskManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        TaskManager taskManager = new TaskManager(tasks);
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 6) {
            System.out.println("1 - Adicionar uma tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Excluir uma tarefa");
            System.out.println("4 - Salvar lista de tarefas");
            System.out.println("5 - Editar uma tarefa");
            System.out.println("6 - Encerrar o programa");
            System.out.print("Escolha: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    taskManager.addTask();
                }
                case 2 -> {
                    taskManager.listTasks();
                }
                case 3 -> {
                    taskManager.removeTask();
                }
                case 4 -> {
                    taskManager.saveTasks();
                }
                case 5 -> {
                    taskManager.editTasks();
                }case 6 -> {
                    System.out.println("\nEncerrando o programa.");
                }
                default -> {
                    System.out.println("\nOpção não encontrada, escolha uma opção valida.\n");
                }
            }
        }
        sc.close();
    }
}
