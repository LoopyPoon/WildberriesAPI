package com.example.wildberriesapi.statistics.incomes;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class ExcelExporter {
    public static void exportToExcel(List<SupplyIncomes> supplyIncomesList, File file) {
        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("MyProjects");

            // Data rows
            int rowNum = 0;

            for (SupplyIncomes supplyIncomes : supplyIncomesList) {

                Row headerRow = sheet.createRow(rowNum);

                headerRow.createCell(0).setCellValue(supplyIncomes.getIncomeId());
                headerRow.createCell(1).setCellValue(supplyIncomes.getNumber());
                headerRow.createCell(2).setCellValue(supplyIncomes.getDate());
                headerRow.createCell(3).setCellValue(supplyIncomes.getLastChangeDate());
                headerRow.createCell(4).setCellValue(supplyIncomes.getSupplierArticle());
                headerRow.createCell(5).setCellValue(supplyIncomes.getTechSize());
                headerRow.createCell(6).setCellValue(supplyIncomes.getBarcode());
                headerRow.createCell(7).setCellValue(supplyIncomes.getQuantity());
                headerRow.createCell(8).setCellValue(supplyIncomes.getTotalPrice());
                headerRow.createCell(9).setCellValue(supplyIncomes.getDateClose());
                headerRow.createCell(10).setCellValue(supplyIncomes.getWarehouseName());
                headerRow.createCell(11).setCellValue(supplyIncomes.getNmId());
                headerRow.createCell(12).setCellValue(supplyIncomes.getStatus());
                rowNum++;

            }

            // Save the workbook to a file
            file.mkdirs();
            log.info("File {} is directory {}", file.getAbsolutePath(), file.isDirectory());
            if (file.isDirectory()) {
                file = new File(file, System.currentTimeMillis() + ".xls");
                file.createNewFile();
            }
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                log.info("Excel file exported successfully! Path: {}", file.getAbsolutePath());
            }
        } catch (IOException e) {
            log.error("Export to excel", e);
        }
    }
}
