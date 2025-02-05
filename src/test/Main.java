package test;

import dominio.Task;
import service.TaskManager;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        TaskManager taskManager = new TaskManager(tasks);
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 6) {
            try {
                System.out.println("1 - Adicionar uma tarefa");
                System.out.println("2 - Listar tarefas");
                System.out.println("3 - Excluir uma tarefa");
                System.out.println("4 - Salvar lista de tarefas");
                System.out.println("5 - Editar uma tarefa");
                System.out.println("6 - Encerrar o programa");
                System.out.print("Escolha: ");
                choice = sc.nextInt();
                sc.nextLine();

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
                        throw new InputMismatchException();
                    }
                }
            }catch (InputMismatchException e){
                System.out.println("\nOpção inválida, por favor informe o número da opção desejada.\n");
                sc.nextLine();
            }
        }
        sc.close();
    }
}
