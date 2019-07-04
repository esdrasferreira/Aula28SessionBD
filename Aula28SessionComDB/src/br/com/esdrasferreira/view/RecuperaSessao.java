package br.com.esdrasferreira.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import br.com.edrasferreira.model.entity.Produto;
import br.com.edrasferreira.model.entity.Usuario;
import br.com.esdrasferreira.model.dao.UsuarioDao;
import br.com.esdrasferreira.model.dao.ProdutoDao;;

@WebServlet({ "/RecuperaSessao", "/area-restrita" })
public class RecuperaSessao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UsuarioDao userDao;

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		Usuario user = null;
		response.setContentType("text/html;charset=UTF-8");
		
		
		HttpSession sessao = request.getSession(true);
		
		try {
			UsuarioDao usuarioDao = new UsuarioDao();
			
			user = usuarioDao.login(usuario, senha);			
			
			usuarioDao.fecharConexao();			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		

		response.setContentType("application/json");

		List<Produto> produtos;
		String json;

		try {
			ProdutoDao dao = new ProdutoDao();
			produtos = dao.todos(1);

			GsonBuilder jsonBuider = new GsonBuilder();
			Gson gsonObejct = jsonBuider.create();

			json = gsonObejct.toJson(produtos);

			dao.fecharConexao();

		} catch (Exception e) {

			e.printStackTrace();
			json = "{\"produto\": \"erro\"}";
		}

		System.out.println(json);

		response.getWriter().append(json);
	}
}
