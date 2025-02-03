package service;

import dominio.Task;
import dominio.TypePeriod;

import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    boolean isEqual;
    private List<Task> tasks;

    Scanner sc = new Scanner(System.in);

    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask() {
        System.out.print("\nTítulo: ");
        String title = sc.nextLine();

        for (Task task : tasks) {
            this.isEqual = task.getTitle().equals(title);
        }

        if (!this.isEqual){
            System.out.print("Descrição: ");
            String description = sc.nextLine();

            System.out.print("Data da realização da tarefa [DD-MM-AAAA]: ");
            String stringValidity = sc.nextLine();

            System.out.print("Período da realização da tarefa: " );
            String stringPeriod = sc.nextLine().toUpperCase();

            String stringPeriodEdited = Normalizer.normalize(stringPeriod, Normalizer.Form.NFD);
            stringPeriodEdited = stringPeriodEdited.replaceAll("[^\\p{ASCII}]", "");
            TypePeriod period = TypePeriod.valueOf(stringPeriodEdited);

            Task task = new Task(title, description, stringValidity, period);
            tasks.add(task);
            System.out.println("Tarefa adicionada.\n");
        }else {
            System.out.println("\nJá existe uma tarefa com este título, por favor crie uma tarefa com um título diferente.\n");
        }

    }

    public void listTasks() {
        if (tasks.isEmpty()){
            System.out.println("\nNenhuma tarefa existente no momento, por favor crie uma tarefa para poder lista-lá.\n");
        }else {
            for (Task listTask : tasks) {
                System.out.println("\n" + listTask);
            }
            System.out.println();
        }

    }

    public void removeTask() {
        if (tasks.isEmpty()){
            System.out.println("\nNenhuma tarefa existente no momento, por favor crie uma tarefa para poder utilizar a opção de remover uma tarefa.\n");
        }else{
            System.out.println("\nListando as tarefas existentes:");
            listTasks();

            System.out.print("Digite o título da tarefa que deseja excluir: ");
            String titleChoice = sc.nextLine();
            System.out.println();

            if (tasks.removeIf(task -> task.getTitle().equals(titleChoice))){
                System.out.println("Tarefa removida com sucesso!\n");
            }else {
                System.out.println("Tarefa " + "\"" + titleChoice + "\"" + " não encontrada.\n");
            }
        }
    }

    public void saveTasks(){
        if (tasks.isEmpty()){
            System.out.println("\nNenhuma tarefa existente no momento, por favor crie uma tarefa para poder salva-lá.\n");
        }else {
            System.out.print("\nDigite o nome que deseja para o arquivo: ");
            String fileName = sc.nextLine();
            String path = "C:\\Users\\conta\\Desktop\\TAREFAS\\" + fileName + ".txt";
            System.out.println();
            try (FileWriter writer = new FileWriter(path)) {
                for (Task task : tasks) {
                    writer.write(task + "\n\n");
                }
                System.out.println("Tarefa salva.\n");
            } catch (IOException e) {
                System.out.println("Erro ao tentar salvar arquivo.");
                e.printStackTrace();
            }
        }
    }
}
