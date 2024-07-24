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
    public static void exportToExcel(List<SupplyOrders> supplyOrdersList, File file, LocalDate dateFrom) {

        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Заказы");

            Row headerRow = sheet.createRow(0);

            String[] headers = {
                    "Дата заказа", "Дата обновления", "Название склада", "Страна", "Округ", "Регион",
                    "Артикул продавца", "Артикул WB", "Баркод", "Категория", "Предмет", "Бренд",
                    "Размер", "Номер поставки", "Договор поставки", "Договор реализации", "Цена без скидок",
                    "Скидка продавца", "Скидка WB", "Цена с учетом всех скидок", "Цена со скидкой продавца",
                    "Отмена заказа", "Дата отмены заказа", "Тип заказа", "Идентификатор стикера", "Номер заказа", "Уникальный идентификатор заказа"
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

            for (SupplyOrders supplyOrder : supplyOrdersList) {
                Row row = sheet.createRow(rowNum++);

                Cell dateCell = row.createCell(0);
                dateCell.setCellValue(dateTimeFormatter.format(supplyOrder.getDate()));
                dateCell.setCellStyle(dateTimeCellStyle);

                Cell lastChangeDateCell = row.createCell(1);
                lastChangeDateCell.setCellValue(dateTimeFormatter.format(supplyOrder.getLastChangeDate()));
                lastChangeDateCell.setCellStyle(dateTimeCellStyle);

                Cell warehouseNameCell = row.createCell(2);
                warehouseNameCell.setCellValue(supplyOrder.getWarehouseName());
                warehouseNameCell.setCellStyle(centerCellStyle);

                Cell countryNameCell = row.createCell(3);
                countryNameCell.setCellValue(supplyOrder.getCountryName());
                countryNameCell.setCellStyle(centerCellStyle);

                Cell oblastOkrugNameCell = row.createCell(4);
                oblastOkrugNameCell.setCellValue(supplyOrder.getOblastOkrugName());
                oblastOkrugNameCell.setCellStyle(centerCellStyle);

                Cell regionNameCell = row.createCell(5);
                regionNameCell.setCellValue(supplyOrder.getRegionName());
                regionNameCell.setCellStyle(centerCellStyle);

                Cell supplierArticleCell = row.createCell(6);
                supplierArticleCell.setCellValue(supplyOrder.getSupplierArticle());
                supplierArticleCell.setCellStyle(centerCellStyle);

                Cell nmIdCell = row.createCell(7);
                nmIdCell.setCellValue(supplyOrder.getNmId());
                nmIdCell.setCellStyle(centerCellStyle);

                Cell barcodeCell = row.createCell(8);
                barcodeCell.setCellValue(supplyOrder.getBarcode());
                barcodeCell.setCellStyle(centerCellStyle);

                Cell categoryCell = row.createCell(9);
                categoryCell.setCellValue(supplyOrder.getCategory());
                categoryCell.setCellStyle(centerCellStyle);

                Cell subjectCell = row.createCell(10);
                subjectCell.setCellValue(supplyOrder.getSubject());
                subjectCell.setCellStyle(centerCellStyle);

                Cell brandCell = row.createCell(11);
                brandCell.setCellValue(supplyOrder.getBrand());
                brandCell.setCellStyle(centerCellStyle);

                Cell techSizeCell = row.createCell(12);
                techSizeCell.setCellValue(supplyOrder.getTechSize());
                techSizeCell.setCellStyle(centerCellStyle);

                Cell incomeIDCell = row.createCell(13);
                incomeIDCell.setCellValue(supplyOrder.getIncomeID());
                incomeIDCell.setCellStyle(centerCellStyle);

                Cell isSupplyCell = row.createCell(14);
                isSupplyCell.setCellValue(supplyOrder.isSupply());
                isSupplyCell.setCellStyle(centerCellStyle);

                Cell isRealizationCell = row.createCell(15);
                isRealizationCell.setCellValue(supplyOrder.isRealization());
                isRealizationCell.setCellStyle(centerCellStyle);

                Cell totalPriceCell = row.createCell(16);
                totalPriceCell.setCellValue(supplyOrder.getTotalPrice());
                totalPriceCell.setCellStyle(centerCellStyle);

                Cell discountPercentCell = row.createCell(17);
                discountPercentCell.setCellValue(supplyOrder.getDiscountPercent());
                discountPercentCell.setCellStyle(centerCellStyle);

                Cell sppCell = row.createCell(18);
                sppCell.setCellValue(supplyOrder.getSpp());
                sppCell.setCellStyle(centerCellStyle);

                Cell finishedPriceCell = row.createCell(19);
                finishedPriceCell.setCellValue(supplyOrder.getFinishedPrice());
                finishedPriceCell.setCellStyle(centerCellStyle);

                Cell priceWithDiscCell = row.createCell(20);
                priceWithDiscCell.setCellValue(supplyOrder.getPriceWithDisc());
                priceWithDiscCell.setCellStyle(centerCellStyle);

                Cell isCancelCell = row.createCell(21);
                isCancelCell.setCellValue(supplyOrder.isCancel());
                isCancelCell.setCellStyle(centerCellStyle);

                Cell cancelDateCell = row.createCell(22);
                cancelDateCell.setCellValue(dateTimeFormatter.format(supplyOrder.getCancelDate()));
                cancelDateCell.setCellStyle(dateTimeCellStyle);

                Cell orderTypeCell = row.createCell(23);
                orderTypeCell.setCellValue(supplyOrder.getOrderType().getDescription());
                orderTypeCell.setCellStyle(centerCellStyle);

                Cell stickerCell = row.createCell(24);
                stickerCell.setCellValue(supplyOrder.getSticker());
                stickerCell.setCellStyle(centerCellStyle);

                Cell gNumberCell = row.createCell(25);
                gNumberCell.setCellValue(supplyOrder.getGNumber());
                gNumberCell.setCellStyle(centerCellStyle);

                Cell sridCell = row.createCell(26);
                sridCell.setCellValue(supplyOrder.getSrid());
                sridCell.setCellStyle(centerCellStyle);
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDateFrom = dateFrom.format(formatter);
            String formattedCurrentDate = LocalDate.now().format(formatter);
            String fileName = "Статистика_Заказы_" + formattedDateFrom + "_" + formattedCurrentDate + ".xls";

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
