

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.co.ca.dao.UserDao;
import jp.co.ca.dao.UserDaoImpl;
import jp.co.ca.entity.User;
/**
 * Servlet implementation class SelectServlet
 */
public class ConnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletReqjuest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    response.setContentType("text/html; charset=EUC-JP");
    StringBuffer stringBuf = new StringBuffer();

    try{
			UserDao dao = new UserDaoImpl();
			List<User> list = dao.findAll();

			for (User u : list) {
				stringBuf.append("<tr><td>"+u.getId()+"</td><td>"+u.getName()+"</td></tr>");
			}


    request.setAttribute("table2", list);
    } finally {

    }

    request.setAttribute("table", stringBuf.toString());
    request.getRequestDispatcher("output.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
