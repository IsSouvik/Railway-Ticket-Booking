package dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Trainticket {
@Id
@GeneratedValue(generator = "pnr")
@SequenceGenerator(initialValue = 4566541,allocationSize = 1,name = "pnr",sequenceName = "pnr")
int pnr;
int trainnumber;
String source;
String destination;
int numberofseats;
double amount;
Date dateofbooking;
Date dateofjourney;
String status;
@ManyToOne
User user;
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public int getPnr() {
	return pnr;
}
public void setPnr(int pnr) {
	this.pnr = pnr;
}
public int getTrainnumber() {
	return trainnumber;
}
public void setTrainnumber(int trainnumber) {
	this.trainnumber = trainnumber;
}

public String getSource() {
	return source;
}
public void setSource(String source) {
	this.source = source;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public int getNumberofseats() {
	return numberofseats;
}
public void setNumberofseats(int numberofseats) {
	this.numberofseats = numberofseats;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public Date getDateofbooking() {
	return dateofbooking;
}
public void setDateofbooking(Date dateofbooking) {
	this.dateofbooking = dateofbooking;
}
public Date getDateofjourney() {
	return dateofjourney;
}
public void setDateofjourney(Date dateofjourney) {
	this.dateofjourney = dateofjourney;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}


}
