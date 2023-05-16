package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Train;
import dto.Trainticket;

public class TrainDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public void save(Train train) {
		transaction.begin();
		manager.persist(train);
		transaction.commit();
	}
	public List<Train>fetchAll(){
		return manager.createQuery("select x from Train x").getResultList();
	}
 public void deletetrain(int trainnumber) {
	 Train train=manager.find(Train.class, trainnumber);
	transaction.begin();
	manager.remove(train);
	transaction.commit();
}
 public Train fetch(int trainnumber) {
	return manager.find(Train.class, trainnumber);
}
 public void update(Train train) {
		transaction.begin();
		manager.merge(train);
		transaction.commit();
	}
 public void save1(Trainticket ticket) {
		transaction.begin();
		manager.persist(ticket);
		transaction.commit();
	}
 public Trainticket fetch1(int trainticket) {
		return manager.find(Trainticket.class,trainticket );
	}

}