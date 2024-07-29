package br.ufg.inf.backend;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calculadora")
public class CalculadoraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String num1 = req.getParameter("num1");
		String num2 = req.getParameter("num2");
		String operacao = req.getParameter("operacao");

		try {
			double numero1 = Double.parseDouble(num1);
			double numero2 = Double.parseDouble(num2);

			String mensagem = "Operação informada é inválida: " + operacao;
			double resultado;
			
			switch (operacao) {
			case "soma":
				resultado = numero1 + numero2;
				mensagem = formatarRetorno(resultado);
				break;

			case "subtracao":
				resultado = numero1 - numero2;
				mensagem = formatarRetorno(resultado);
				break;

			case "divisao":
				try {
					resultado = numero1 / numero2;
					mensagem = formatarRetorno(resultado);					
				} catch (ArithmeticException e) {
					resp.getWriter().println("Não é possível fazer divisão por zero.");
					resp.setStatus(400);
					return;
				}
				break;

			case "multiplicacao":
				resultado = numero1 * numero2;
				mensagem = formatarRetorno(resultado);
				break;

			}
			resp.getWriter().println(mensagem);
		} catch (NullPointerException | NumberFormatException e) {
			resp.getWriter().println("Informe valores válidos para os parametros num1, num2 e operacao");
			resp.setStatus(412);
		}
	}

	private String formatarRetorno(double resultado) {
		return "Resultado da Operação: " + resultado;
	}
}
