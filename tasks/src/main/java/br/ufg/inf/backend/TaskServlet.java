package br.ufg.inf.backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {
    private List<String> tasks = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (tasks.isEmpty()) {
            response.getWriter().write("Não há tarefas disponíveis.");
        } else {
            for (String task : tasks) {
                response.getWriter().write(task + "\n");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newTask = request.getParameter("task");
        if (newTask != null && !newTask.isEmpty()) {
            tasks.add(newTask);
            response.getWriter().write("Tarefa adicionada com sucesso!");
        } else {
            response.getWriter().write("Erro: a tarefa não pode estar vazia.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String indexParam = request.getParameter("index");
        String updatedTask = request.getParameter("task");

        try {
            int index = Integer.parseInt(indexParam) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.set(index, updatedTask);
                response.getWriter().write("Tarefa atualizada com sucesso!");
            } else {
                response.getWriter().write("Erro: índice inválido.");
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("Erro: índice deve ser um número inteiro.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String indexParam = request.getParameter("index");

        try {
            int index = Integer.parseInt(indexParam) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.remove(index);
                response.getWriter().write("Tarefa removida com sucesso!");
            } else {
                response.getWriter().write("Erro: índice inválido.");
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("Erro: índice deve ser um número inteiro.");
        }
    }
}