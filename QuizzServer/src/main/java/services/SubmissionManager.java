package services;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import data.StudentDAO;
import data.SubmissionDAO;
import model.Submission;
import utils.ExcelWriter;

public class SubmissionManager {
    private static SubmissionManager instance = null;

    public static SubmissionManager getInstance() {
        if (instance == null) {
            instance = new SubmissionManager();
        }
        return instance;
    }

    public boolean createSubmission(Submission submission) {
        return new SubmissionDAO().create(submission);
    }

    public boolean updateSubmission(Submission submission) {
        return new SubmissionDAO().update(submission);
    }

    public boolean deleteSubmission(int submissionId) {
        return new SubmissionDAO().delete(submissionId);
    }

    public boolean exportSubmissions(String sheetname, String excelFilePath, List<Submission> listSubmissions) {

        try {
            new SubmissionExcelWriter().writeExcel(sheetname, listSubmissions, excelFilePath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}

class SubmissionExcelWriter extends ExcelWriter {
    @Override
    public void writeHeader(Sheet sheet, int rowIndex) {
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(rowIndex);
        Cell cell = null;
        cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ID");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("StudentID");

        // cell = row.createCell(2);
        // cell.setCellStyle(cellStyle);
        // cell.setCellValue("Date");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Time Taken");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Score");
    }

    @Override
    public <T> void writeData(T data, Row row) {
        Submission submission = (Submission) data;

        Cell cell = null;
        int colIndex = 0;

        cell = row.createCell(colIndex++);
        cell.setCellValue(submission.getSubmissionId());

        cell = row.createCell(colIndex++);
        cell.setCellValue(submission.getStudentId());

        cell = row.createCell(colIndex++);
        cell.setCellValue(submission.getTimeTaken());

        cell = row.createCell(colIndex++);
        cell.setCellValue(submission.getScore());
    }

}