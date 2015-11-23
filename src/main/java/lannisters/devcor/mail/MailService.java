package lannisters.devcor.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import lannisters.devcor.entity.Comment;
import lannisters.devcor.entity.Order;
import lannisters.devcor.entity.Player;
import lannisters.devcor.mail.MailSender;
import lannisters.devcor.service.PlayersService;
import lannisters.devcor.util.OrderAndComment;

/**
 * @author xoma0_000
 */
@Service
@Configurable
public class MailService {
	private static final String REGISTRATION = "Authorization Information";
	private static final String ORDER_CREATED = "Order created";
	private static final String STATUS_CHANGE = "Status on your order has changed";
	private static final String COMMENT = "New comment";
	private static final String ADMINISTRATOR = "Room: 1-101; E-mail: admin@ukma.kiev.ua;"
			+ " Phone number: 444-44-44, 8-093-999-99-99";
	@Autowired
	private MailSender sender;
	@Autowired
	private PlayersService service;

	/**
	 * It will send an e-mail notification to a new player, to inform him that
	 * his account has been created.
	 * 
	 * @param receiver
	 *            - The one who will get email
	 * @param password
	 *            - password created by administrator
	 */
	public void registrationEmail(Player user) {
		String header = "Welcome to our system, ";
		String name = user.getFullName();
		String technician = "";
		if(user.getRoleId()==2)
			technician = ",as a technician ";
		String text = "! \nYou have been registered in DevCor system " +technician+ "with \nLogin: ";
		String pwd = "\nPassword: ";
		String footer = "\nLater you can change your password. If you have any questions "
				+ "be sure to contact with administrator:\n";
		String email = user.getPlayerEmail();
		String password = user.getPassword();
		
		sender.send(email, REGISTRATION, header + name + text + email + pwd + password + footer + ADMINISTRATOR);
	}

	/**
	 * It will send an e-mail notification to the user, to inform him about
	 * status changing of his order.
	 * 
	 * @param order
	 *            - An order which status had been changed
	 */
	public void statusEmail(Order order) {
		String message = "Status on your order from " + order.getCreationDate() + "" + "has changed to "
				+ order.getExecutionStatus();

		int playerId = order.getAuthorId();
		Player player = service.getPlayerById(playerId);
		String receiver = player.getPlayerEmail();
		sender.send(receiver, STATUS_CHANGE, message);
	}

	public void commentEmail(OrderAndComment orderAndComment) {
		String message = "Technician responsible for your order has left this comment: \n";
		Order order = orderAndComment.getOrder();
		Comment comment = orderAndComment.getComment();
		String mesComment = comment.getComment();
		int playerId = order.getAuthorId();
		Player player = service.getPlayerById(playerId);
		String receiver = player.getPlayerEmail();
		sender.send(receiver, COMMENT, message+mesComment);
	}

	/**
	 * It will send an e-mail notification to both user and technician, who are
	 * concerned to this order, to inform them that the new Order has been
	 * created.
	 * 
	 * @param order
	 *            - A newly created order
	 */
	public void orderCreatEmail(Order order) {
		int userId = order.getAuthorId();
		Player user = service.getPlayerById(userId);
		String receiver1 = user.getPlayerEmail();

		int technicianId = order.getTechnicianId();
		Player technician = service.getPlayerById(technicianId);
		String receiver2 = technician.getPlayerEmail();
		String messageUser = "Your order has been succesfully created! " + "Technician responsible for it is "
				+ technician.getFirstName() + "" + " " + technician.getLastName() + ". It is predicted to be "
				+ "made by " + order.getDueDate();
		sender.send(receiver1, ORDER_CREATED, messageUser);
		String messageTechnician = "There is an order for you! It must be done by " + order.getDueDate();
		sender.send(receiver2, ORDER_CREATED, messageTechnician);
	}
}