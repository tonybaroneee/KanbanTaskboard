package kanbanbackend.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import kanbanbackend.model.Project;
import kanbanbackend.util.ProjectLoader;
import kanbanbackend.util.ReportGenerator;
import org.w3c.dom.NodeList;

public class HistoryServlet extends HttpServlet {

        // http://forums.adobe.com/message/575176

        /*
         * Initialize this servlet
         */
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}

        /*
         * Respond to a DO HTTP request
         */
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

        /*
         * Respond to a POST HTTP request
         */
	public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException,
            FileNotFoundException {

            response.setContentType("application/pdf");
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            System.out.println("DEBUG: project id = " + projectId);
            ProjectLoader loader = new ProjectLoader();
            Project proj = loader.loadProject(projectId);
            Document document = new Document();

            // try to generate the report
            try {
                ReportGenerator gen = new ReportGenerator(proj);
                gen.createReport(document, response);
                document.close();
            } catch (DocumentException e1) {
                e1.printStackTrace();
            } catch (SQLException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
	}

}
