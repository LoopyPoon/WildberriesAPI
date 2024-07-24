package com.example.wildberriesapi.statistics.orders;

import com.example.wildberriesapi.statistics.stocks.SupplyStocks;
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
public class OrdersExcelExporter {
    public static void exportToExcel(List<SupplyOrders> supplyStocksList, File file, LocalDate dateFrom) {

        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Заказы");

            Row headerRow = sheet.createRow(0);

            String[] headers = {
                    "Дата обновления", "Название склада", "Артикул продавца", "Артикул WB", "Баркод",
                    "Количество", "В пути к клиенту", "В пути от клиента", "Полное количество",
                    "Категория", "Предмет", "Бренд", "Размер", "Цена", "Скидка",
                    "Договор поставки", "Договор реализации", "Код контракта"
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

            for (SupplyStocks supplyStocks : supplyStocksList) {
                Row row = sheet.createRow(rowNum++);

                Cell lastChangeDateCell = row.createCell(0);
                lastChangeDateCell.setCellValue(dateTimeFormatter.format(supplyStocks.getLastChangeDate()));
                lastChangeDateCell.setCellStyle(dateTimeCellStyle);

                Cell warehouseNameCell = row.createCell(1);
                warehouseNameCell.setCellValue(supplyStocks.getWarehouseName());
                warehouseNameCell.setCellStyle(centerCellStyle);

                Cell supplierArticleCell = row.createCell(2);
                supplierArticleCell.setCellValue(supplyStocks.getSupplierArticle());
                supplierArticleCell.setCellStyle(centerCellStyle);

                Cell nmIdCell = row.createCell(3);
                nmIdCell.setCellValue(supplyStocks.getNmId());
                nmIdCell.setCellStyle(centerCellStyle);

                Cell barcodeCell = row.createCell(4);
                barcodeCell.setCellValue(supplyStocks.getBarcode());
                barcodeCell.setCellStyle(centerCellStyle);

                Cell quantityCell = row.createCell(5);
                quantityCell.setCellValue(supplyStocks.getQuantity());
                quantityCell.setCellStyle(centerCellStyle);

                Cell inWayToClientCell = row.createCell(6);
                inWayToClientCell.setCellValue(supplyStocks.getInWayToClient());
                inWayToClientCell.setCellStyle(centerCellStyle);

                Cell inWayFromClientCell = row.createCell(7);
                inWayFromClientCell.setCellValue(supplyStocks.getInWayFromClient());
                inWayFromClientCell.setCellStyle(centerCellStyle);

                Cell quantityFullCell = row.createCell(8);
                quantityFullCell.setCellValue(supplyStocks.getQuantityFull());
                quantityFullCell.setCellStyle(centerCellStyle);

                Cell categoryCell = row.createCell(9);
                categoryCell.setCellValue(supplyStocks.getCategory());
                categoryCell.setCellStyle(centerCellStyle);

                Cell subjectCell = row.createCell(10);
                subjectCell.setCellValue(supplyStocks.getSubject());
                subjectCell.setCellStyle(centerCellStyle);

                Cell brandCell = row.createCell(11);
                brandCell.setCellValue(supplyStocks.getBrand());
                brandCell.setCellStyle(centerCellStyle);

                Cell techSizeCell = row.createCell(12);
                techSizeCell.setCellValue(supplyStocks.getTechSize());
                techSizeCell.setCellStyle(centerCellStyle);

                Cell priceCell = row.createCell(13);
                priceCell.setCellValue(supplyStocks.getPrice());
                priceCell.setCellStyle(centerCellStyle);

                Cell discountCell = row.createCell(14);
                discountCell.setCellValue(supplyStocks.getDiscount());
                discountCell.setCellStyle(centerCellStyle);

                Cell isSupplyCell = row.createCell(15);
                isSupplyCell.setCellValue(supplyStocks.isSupply());
                isSupplyCell.setCellStyle(centerCellStyle);

                Cell isRealizationCell = row.createCell(16);
                isRealizationCell.setCellValue(supplyStocks.isRealization());
                isRealizationCell.setCellStyle(centerCellStyle);

                Cell sCCodeCell = row.createCell(17);
                sCCodeCell.setCellValue(supplyStocks.getSCCode());
                sCCodeCell.setCellStyle(centerCellStyle);
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDateFrom = dateFrom.format(formatter);
            String formattedCurrentDate = LocalDate.now().format(formatter);
            String fileName = "Статистика_Склад_" + formattedDateFrom + "_" + formattedCurrentDate + ".xls";

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
