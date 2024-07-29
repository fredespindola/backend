package br.ufg.inf.backend;

import java.io.FileNotFoundException;

public class TaskServiceWithNotification extends TaskService {
	
	@Override
	public String list() throws FileNotFoundException {
		String resposta = super.list();
		System.out.println("O serviço de listagem de tarefas foi requisitado.");
		return resposta;
	}
	
	@Override
	public String add(String task) {
		String resposta = super.add(task);
		System.out.println("O serviço de adição de tarefas foi requisitado.");
		return resposta;
	}
	
	@Override
	public String update(int index, String task) {
		String resposta = super.update(index, task);
		System.out.println("O serviço de atualização de tarefas foi requisitado.");
		return resposta;
	}
	
	@Override
	public String remove(int index) {
		String resposta = super.remove(index);
		System.out.println("O serviço de exclusão de tarefas foi requisitado.");
		return resposta;
	}
	
}
