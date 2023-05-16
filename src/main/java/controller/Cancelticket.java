package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TrainDao;
import dao.UserDao;
import dto.Train;
import dto.Trainticket;
import dto.User;

@WebServlet("/cancelticket")
public class Cancelticket extends HttpServlet{
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	User user = (User)req.getSession().getAttribute("user");
	if (user == null) {
		resp.getWriter().print("<h1 style='color:red'>Session Expired login again</h1>");
		req.getRequestDispatcher("login.html").include(req, resp);
	} else {
		int pnr=Integer.parseInt(req.getParameter("pnr"));
		TrainDao dao=new TrainDao();
		Trainticket ticket=dao.fetch1(pnr);
		if(ticket==null) {
			resp.getWriter().print("<h1 style='color:red'>Ticket Not Found</h1>");
			req.getRequestDispatcher("userhome.html").include(req, resp);	
		}else {
			if(Period.between(LocalDate.now(), ticket.getDateofjourney().toLocalDate()).getDays()<=0)
			{
				resp.getWriter().print("<h1 style='color:red'>You can not cancel this ticket</h1>");
				req.getRequestDispatcher("userhome.html").include(req, resp);
			}	
			else {
			ticket.setStatus("Cancelled");
			Train train=dao.fetch(ticket.getTrainnumber());
			train.setSeat(train.getSeat()+ticket.getNumberofseats());
			user.setWallet(user.getWallet()+ticket.getAmount());
			UserDao dao1=new UserDao();
			dao1.update(user);
			dao.update(train);
			resp.getWriter().print("<h1>Ticket Cancelled Successfull</h1>");
			req.getRequestDispatcher("userhome.html").include(req, resp);
			}
			
		}
}
}
}
