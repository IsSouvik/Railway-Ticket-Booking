package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TrainDao;
import dto.Train;
@WebServlet("/fetchalltrain")
public class FetchallTrain extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TrainDao dao = new TrainDao();
		List<Train> list = dao.fetchAll();
			if(list.isEmpty()) {
			resp.getWriter().print("<h1 style='color:red'>No RailWay Information Added</h1>");
			req.getRequestDispatcher("managementhome.html").include(req, resp);
		}else {
			req.setAttribute("list", list);
			req.getRequestDispatcher("fetchrailwayinfo.jsp").forward(req, resp);
		}

	}
}
