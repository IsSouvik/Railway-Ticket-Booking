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

@WebServlet("/deletetrain")
public class Deletetrain extends HttpServlet{
@Override
protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	int trainnumber=Integer.parseInt(req.getParameter("trainnumber"));
	TrainDao dao=new TrainDao();
	dao.deletetrain(trainnumber);
	res.getWriter().print("<h1>Deleted Succesfully</h1>");
	List<Train>list=dao.fetchAll();
	if(list.isEmpty()) {
		res.getWriter().print("<h1 style='color:red'>No RailWay Information Found</h1>");
		req.getRequestDispatcher("managementhome.html").include(req, res);
	}else {
		res.getWriter().print("Deleted Succesfully");
		req.setAttribute("list", list);
		req.getRequestDispatcher("fetchrailwayinfo.jsp").forward(req, res);
	}
	
}
}
