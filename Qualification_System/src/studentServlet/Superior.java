package studentServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import masteDAO.QualificationDAO;
import masteDTO.QualificationDTO;


/**
 * Servlet implementation class Superior
 */
@WebServlet("/Superior")
public class Superior extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Superior() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest re, HttpServletResponse response) throws ServletException, IOException {
		re.setCharacterEncoding("UTF-8");
		String view = null;
		HttpSession s = re.getSession();
		int sid = 0;
		try {
			//学籍番号
			sid = (int) s.getAttribute("id");
			//自身が合格した資格の上位に値する資格情報を取得する
			ArrayList<QualificationDTO> two = QualificationDAO.search1(sid);
			s.setAttribute("Super", two);
			view = "/WEB-INF/student/super.jsp";
		} catch (NumberFormatException e) {
			view = "/WEB-INF/student/super.jsp";
			s.setAttribute("status", "Number");
			e.getStackTrace();
			System.out.println(e);
		} catch (NullPointerException e) {
			view = "/WEB-INF/student/super.jsp";
			s.setAttribute("status", "nai");
			e.getStackTrace();
			System.out.println(e);
		} catch (Exception e) {
			view = "/WEB-INF/student/super.jsp";
			s.setAttribute("status", "Exception");
			e.getStackTrace();
			System.out.println(e);
		} finally {
			RequestDispatcher dispatcher = re.getRequestDispatcher(view);
			dispatcher.forward(re, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
