/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import java.util.ArrayList;

/**
 *
 * @author dedhi
 */
public class PdfDashboardView extends AbstractPdfView{
    @Override
	protected void buildPdfDocument(Map model, Document document,
		PdfWriter writer, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

//		Map<String,String> revenueData = (Map<String,String>) model.get("revenueData");
Map<String, ArrayList<Integer>> domainMap = (Map<String,ArrayList<Integer>>) model.get("domainMap");
Map<String, ArrayList<Integer>> questionMap = (Map<String,ArrayList<Integer>>) model.get("questionMap");
Map<String, ArrayList<Integer>> answerMap = (Map<String,ArrayList<Integer>>) model.get("answerMap");

		
		Table table = new Table(3) {};
                table.setPadding(5);
                table.setCellsFitPage(true);
		table.addCell("Domain Name");
		table.addCell("Total Questions");
                table.addCell("Total Answers");
		
		for (Map.Entry<String, ArrayList<Integer>> entry : domainMap.entrySet()) {
			table.addCell(entry.getKey());
			table.addCell(String.valueOf(entry.getValue().get(0)));
                        table.addCell(String.valueOf(entry.getValue().get(1)));
                }
                table.addCell("");
		document.add(table);
                
                Table questionTable = new Table(7){};
                questionTable.setPadding(5);
                questionTable.setCellsFitPage(true);
                questionTable.addCell("Username");
                questionTable.addCell("Total No. of Questions");
                questionTable.addCell("Total No. of Questions not approved");
                questionTable.addCell("Total No. of Questions approved");
                questionTable.addCell("Total No. of Questions rejected");
                questionTable.addCell("Total likes");
                questionTable.addCell("Total Dislikes");
                
                for (Map.Entry<String, ArrayList<Integer>> entry : questionMap.entrySet()) {
			questionTable.addCell(entry.getKey());
			questionTable.addCell(String.valueOf(entry.getValue().get(0)));
                        questionTable.addCell(String.valueOf(entry.getValue().get(1)));
                        questionTable.addCell(String.valueOf(entry.getValue().get(2)));
                        questionTable.addCell(String.valueOf(entry.getValue().get(3)));
                        questionTable.addCell(String.valueOf(entry.getValue().get(4)));
                        questionTable.addCell(String.valueOf(entry.getValue().get(5)));
                }
                questionTable.addCell("");
		document.add(questionTable);
                
                Table answerTable = new Table(7){};
                answerTable.setPadding(5);
                answerTable.addCell("Username");
                answerTable.addCell("Total No. of Answers");
                answerTable.addCell("Total No. of Answers not approved");
                answerTable.addCell("Total No. of Answers approved");
                answerTable.addCell("Total No. of Answers rejected");
                answerTable.addCell("Total likes");
                answerTable.addCell("Total Dislikes");
                
                for (Map.Entry<String, ArrayList<Integer>> entry : answerMap.entrySet()) {
			answerTable.addCell(entry.getKey());
			answerTable.addCell(String.valueOf(entry.getValue().get(0)));
                        answerTable.addCell(String.valueOf(entry.getValue().get(1)));
                        answerTable.addCell(String.valueOf(entry.getValue().get(2)));
                        answerTable.addCell(String.valueOf(entry.getValue().get(3)));
                        answerTable.addCell(String.valueOf(entry.getValue().get(4)));
                        answerTable.addCell(String.valueOf(entry.getValue().get(5)));
                }
                
		document.add(answerTable);
                
	}
}
