package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Order;

public interface OrdersService {

	public List<Order> getAllOrders();

	public Order getOrderById(int orderId);

	public void addOrder(Order order) throws SQLException;

	public void updateOrder(Order order) throws SQLException;

	public void deleteOrder(int orderId) throws SQLException;

	public List<Order> getAllOrdersOfUser(String email) throws SQLException;

	public List<Order> getAllOrdersOfTechnician(String email) throws SQLException;

	public List<Order> getAllOrdersSorted() throws SQLException;

	public List<Order> getAllOrdersOfRoom(int roomId);

	List<Order> getAllOrdersOfRoomNoDevice(int roomId) throws SQLException;

	List<Order> getAllOrdersOfRoomWithDevice(int roomId) throws SQLException;

	public List<Order> getOrdersByUrgency(int urgencyStatusId);

	public List<Order> getOrdersByProblem(int problemTypeId);
	
	public boolean checkUrgencyStatusOrders(int urgencyStatusId);
	
	public boolean checkProblemTypeOrders(int problemTypeId);
}
