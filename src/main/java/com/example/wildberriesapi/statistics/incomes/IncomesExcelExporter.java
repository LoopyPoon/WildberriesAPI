package com.example.wildberriesapi.statistics.incomes;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class IncomesExcelExporter {
    public static void exportToExcel(List<SupplyIncomes> supplyIncomesList, File file, LocalDate dateFrom) {
        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Поставки");

            Row headerRow = sheet.createRow(0);

            String[] headers = {
                    "Номер поставки", "Номер УПД", "Дата поступления", "Дата обновления", "Артикул продавца",
                    "Размер товара", "Бар-код", "Количество", "Цена из УПД", "Дата принятия", "Название склада",
                    "Артикул WB", "Текущий статус поставки"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            CellStyle dateTimeCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateTimeCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy HH:mm:ss"));
            dateTimeCellStyle.setAlignment(HorizontalAlignment.CENTER);

            CellStyle centerCellStyle = workbook.createCellStyle();
            centerCellStyle.setAlignment(HorizontalAlignment.CENTER);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            // Data rows
            int rowNum = 1;

            for (SupplyIncomes supplyIncomes : supplyIncomesList) {
                Row row = sheet.createRow(rowNum++);

                Cell incomeIdCell = row.createCell(0);
                incomeIdCell.setCellValue(supplyIncomes.getIncomeId());
                incomeIdCell.setCellStyle(centerCellStyle);

                Cell numberCell = row.createCell(1);
                numberCell.setCellValue(supplyIncomes.getNumber());
                incomeIdCell.setCellStyle(centerCellStyle);

                Cell dateCell = row.createCell(2);
                dateCell.setCellValue(dateTimeFormatter.format(supplyIncomes.getDate()));
                dateCell.setCellStyle(dateTimeCellStyle);

                Cell lastChangeDateCell = row.createCell(3);
                lastChangeDateCell.setCellValue(dateTimeFormatter.format(supplyIncomes.getLastChangeDate()));
                lastChangeDateCell.setCellStyle(dateTimeCellStyle);

                Cell supplierArticleCell = row.createCell(4);
                supplierArticleCell.setCellValue(supplyIncomes.getSupplierArticle());
                supplierArticleCell.setCellStyle(centerCellStyle);

                Cell techSizeCell = row.createCell(5);
                techSizeCell.setCellValue(supplyIncomes.getTechSize());
                techSizeCell.setCellStyle(centerCellStyle);

                Cell barcodeCell = row.createCell(6);
                barcodeCell.setCellValue(supplyIncomes.getBarcode());
                barcodeCell.setCellStyle(centerCellStyle);

                Cell quantityCell = row.createCell(7);
                quantityCell.setCellValue(supplyIncomes.getQuantity());
                quantityCell.setCellStyle(centerCellStyle);

                Cell totalPriceCell = row.createCell(8);
                totalPriceCell.setCellValue(supplyIncomes.getTotalPrice());
                totalPriceCell.setCellStyle(centerCellStyle);

                Cell dateCloseCell = row.createCell(9);
                dateCloseCell.setCellValue(dateTimeFormatter.format(supplyIncomes.getDateClose()));
                dateCloseCell.setCellStyle(dateTimeCellStyle);

                Cell warehouseNameCell = row.createCell(10);
                warehouseNameCell.setCellValue(supplyIncomes.getWarehouseName());
                warehouseNameCell.setCellStyle(centerCellStyle);

                Cell nmIdCell = row.createCell(11);
                nmIdCell.setCellValue(supplyIncomes.getNmId());
                nmIdCell.setCellStyle(centerCellStyle);

                Cell statusCell = row.createCell(12);
                statusCell.setCellValue(supplyIncomes.getStatus());
                statusCell.setCellStyle(centerCellStyle);
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDateFrom = dateFrom.format(formatter);
            String formattedCurrentDate = LocalDate.now().format(formatter);
            String fileName = "Статистика_Поставки_" + formattedDateFrom + "_" + formattedCurrentDate + ".xls";

            // Save the workbook to a file
            if (file.isDirectory()) {
                file = new File(file, fileName);
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
