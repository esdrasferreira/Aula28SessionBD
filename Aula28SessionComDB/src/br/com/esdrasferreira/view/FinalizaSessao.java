package br.com.esdrasferreira.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet({ "/FinalizaSessao", "/finaliza-sessao" })
public class FinalizaSessao extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession sessao = request.getSession(true);
		sessao.removeAttribute("id"); //mata a sessão
		
		response.sendRedirect("area-login");
	}

}
