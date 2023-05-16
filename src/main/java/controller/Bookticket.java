package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

@WebServlet("/bokticket")
public class Bookticket extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	User user=(User)req.getSession().getAttribute("user");
	if(user==null) {
		resp.getWriter().print("<h1 style='color:red'>Session Expired Login Again</h1>");
		req.getRequestDispatcher("home.html").include(req, resp);
	}

		else {
		int no=Integer.parseInt(req.getParameter("tn"));
		TrainDao dao=new TrainDao();
		Train train=dao.fetch(no);
		String from=req.getParameter("from");
		String to=req.getParameter("to");
		int seats=Integer.parseInt(req.getParameter("seats"));
		Date doj=Date.valueOf(req.getParameter("doj"));
		Date dob=Date.valueOf(LocalDate.now());
//		double amount=Double.parseDouble(train.getPrice())*Double.parseDouble(req.getParameter("seats"));
		
		if(seats<=0) {
			resp.getWriter().print("<h1>Seat can't Be Less Than One</h1>");
			req.getRequestDispatcher("userhome.html").include(req, resp);	
		}
		else {
		if(from.equals(to)) {
			resp.getWriter().print("<h1>Source And Destination Can't be Same</h1>");
			req.getRequestDispatcher("userhome.html").include(req, resp);
		}else {
			int frompos=0;
			int topos=0;
			String[] str=train.getStations().split(",");
			for (int i=0;i<str.length;i++) {
				if(str[i].equals(from)) {
					frompos=i;
					
				}
				if(str[i].equals(to)) {
					topos=i;
				}
			}
			if(frompos>topos) {
				resp.getWriter().print("<h1>Select Valid Source And Destination</h1>");
				req.getRequestDispatcher("userhome.html").include(req, resp);
			}else {
				String []str1=train.getPrice().split(",");
				double price=Double.parseDouble(str1[topos])-Double.parseDouble(str1[frompos]);
				double amount=seats*price;
				boolean flag=true;
				String[] str2=train.getDays().split(",");
				for(String day:str2)
				{
					if(day.equalsIgnoreCase(doj.toLocalDate().getDayOfWeek().getDisplayName(TextStyle.FULL,Locale.ENGLISH)))
							{
						flag=false;
							}
				}
				
				if(Period.between(dob.toLocalDate(),doj.toLocalDate()).getDays()<0 || flag)
				{
					resp.getWriter().print("<h1>Train Not Available in the selected date </h1>");
					req.getRequestDispatcher("userhome.html").include(req, resp);
				}
				else {
					if(user.getWallet()<amount)
					{
						resp.getWriter().print("<h1>Insufficient funds for booking ticket</h1>");
						req.getRequestDispatcher("userhome.html").include(req, resp);
					}else {
						if(train.getSeat()<=0) {
							resp.getWriter().print("<h1>Seat is Not Avalaible</h1>");
							req.getRequestDispatcher("userhome.html").include(req, resp);	
						}
					
					else {
						Trainticket ticket=new Trainticket();
						ticket.setTrainnumber(no);
						ticket.setSource(from);
						ticket.setDestination(to);
						ticket.setNumberofseats(seats);
						ticket.setAmount(amount);
						ticket.setDateofbooking(dob);
						ticket.setDateofjourney(doj);
						ticket.setUser(user);
						ticket.setStatus("Booked");
						
						dao.save1(ticket);
						
						train.setSeat(train.getSeat()-seats);
						TrainDao dao2=new TrainDao();
						dao2.update(train);
						List<Trainticket> tickets=user.getticket();
						if(tickets==null)
						{
							tickets=new ArrayList<>();
						}
						tickets.add(ticket);
						user.setticket(tickets);
						user.setWallet(user.getWallet()-amount);
						UserDao dao3=new UserDao();
						dao3.update(user);
						resp.getWriter().print("<h1 style='color:green'>Ticket Booked Succesfully</h1>");
						req.getRequestDispatcher("userhome.html").include(req, resp);
					}
				}
			}
		}
		}
}
}
}
}

