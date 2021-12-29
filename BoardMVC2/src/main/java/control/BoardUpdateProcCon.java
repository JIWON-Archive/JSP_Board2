package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;

@WebServlet("/BoardUpdateProcCon.do")
public class BoardUpdateProcCon extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException {
		// �ѱ� ó��
		request.setCharacterEncoding("UTF-8");
		// ������ �Ѿ�� �����͸� �޾���
		int num = Integer.parseInt(request.getParameter("num"));
		String password = request.getParameter("password"); // ����ڷκ��� �Է� ���� �н�����
		String pass = request.getParameter("pass"); // ���� �����ͺ��̽��� ����Ǿ� �ִ� �н����� ����
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		// password ���� pass ���� ��
		if(password.equals(pass)) { // �н����尡 ���ٸ� �����͸� ����
			BoardDAO bdao = new BoardDAO(); // ��ü ���� ���� -> ��� ����, ��������� ȣ���Ϸ���
			bdao.updateBoard(num, subject, content);
			// ������ �Ϸ�Ǿ��ٸ� ��ü �Խñ� ����� �̵�
//			request.setAttribute("msg", "������ �Ϸ�Ǿ����ϴ�.");
			RequestDispatcher dis = request.getRequestDispatcher("BoardListContoller.do");
			dis.forward(request, response); 
	
		} else {
			// ��й�ȣ�� Ʋ�ȴٸ� ���� �������� �̵�
			request.setAttribute("msg", "1");
			RequestDispatcher dis = request.getRequestDispatcher("BoardListContoller.do");
			dis.forward(request, response); 
	
		}
	}
}