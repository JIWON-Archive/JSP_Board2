package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardBean;
import model.BoardDAO;

// �ڡ� ������ Ŭ������ ���� ���� ���� �ڡ�
@WebServlet("/BoardListContoller.do")
public class BoardListCon extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);

	}

	protected void reqPro(HttpServletRequest reqeust, HttpServletResponse response) throws ServletException, IOException {
		// ȭ�鿡 ������ �Խñ��� ������ ����
		int pageSize = 10;
		// ���� �������� �ִ� �������� �ѹ����� �о����
		// ���� ī���͸� Ŭ���� ��ȣ ���� �о��
		String pageNum = reqeust.getParameter("pageNum");
		// ���� ó�� boardList.jsp�� Ŭ���ϰų� ���� ���� �� �ٸ� �Խñۿ��� �� �������� �Ѿ����
		// pageNum ���� ���⿡ null �� ó��
		if(pageNum == null) {
			pageNum = "1";
		}
		// ��ü �Խñ��� ����
		int count = 0;
		// jsp ������ ������ ������ �ѹ��� ���ڸ� �����ϴ� ����
		int number = 0;
		
		// ���� �������� �ִ� ������ ���ڸ� ���ڷ� ��ȯ
		int currentPage = Integer.parseInt(pageNum);
		// ��ü �Խñ��� ������ �����;� �ϱ⿡ �����ͺ��̽� ��ü ����
		BoardDAO bdao = new BoardDAO();
		// ��ü �Խñ��� ������ �о� ���� �޼ҵ� ȣ��
		count = bdao.getAllCount();
		
		// ���� ������ ������ ���� ��ȣ�� ����
		int startRow = (currentPage-1) * pageSize+1;
		int endRow = currentPage * pageSize;
		
		// �ֽű� 10���� �������� �Խñ��� ���� �޾��ִ� �޼ҵ� ȣ��
		// �������� ����(������)�� ����(BoardBean)�� �ְ� �ڽ�(Vector)�� ��Ƽ� �������� �����͸� ����
		ArrayList<BoardBean> list = bdao.getAllBoard(startRow, endRow); 
		number = count - (currentPage - 1) * pageSize;
		
		///// ���� ������ ��й�ȣ�� Ʋ�ȴٸ�
		String msg = (String) reqeust.getAttribute("msg");
		/// boardList.jsp ������ request ��ü�� ��Ƽ� �Ѱ���
		// ArrayList�� �ֽű� 10���� �������� �޴´�.
		reqeust.setAttribute("list", list);
		reqeust.setAttribute("number", number);
		reqeust.setAttribute("pageSize", pageSize);
		reqeust.setAttribute("count", count);
		reqeust.setAttribute("currentPage", currentPage);
		reqeust.setAttribute("msg", msg);
		
		
		RequestDispatcher dis = reqeust.getRequestDispatcher("boardList.jsp");
		dis.forward(reqeust, response);
		
	}
		
}