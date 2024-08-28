package com.example.wildberriesapi.service.statistics.sales;

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
public class SalesExcelExporter {
    public static void exportToExcel(List<SupplySales> supplySalesList, File file, LocalDate dateFrom) {

        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Продажи");

            Row headerRow = sheet.createRow(0);

            String[] headers = {
                    "Дата продажи", "Дата обновления", "Название склада", "Страна", "Округ", "Регион",
                    "Артикул продавца", "Артикул WB", "Баркод", "Категория", "Предмет", "Бренд",
                    "Размер", "Номер поставки", "Договор поставки", "Договор реализации", "Цена без скидок",
                    "Скидка продавца", "Скидка WB", "Оплачено с WB Кошелька", "К перечислению продавцу",
                    "Фактическая цена", "Цена со скидкой продавца", "Уникальный идентификатор продажи",
                    "Тип заказа", "Идентификатор стикера", "Номер заказа", "Уникальный идентификатор заказа"
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

            for (SupplySales supplySale : supplySalesList) {
                Row row = sheet.createRow(rowNum++);

                Cell dateCell = row.createCell(0);
                dateCell.setCellValue(dateTimeFormatter.format(supplySale.getDate()));
                dateCell.setCellStyle(dateTimeCellStyle);

                Cell lastChangeDateCell = row.createCell(1);
                lastChangeDateCell.setCellValue(dateTimeFormatter.format(supplySale.getLastChangeDate()));
                lastChangeDateCell.setCellStyle(dateTimeCellStyle);

                Cell warehouseNameCell = row.createCell(2);
                warehouseNameCell.setCellValue(supplySale.getWarehouseName());
                warehouseNameCell.setCellStyle(centerCellStyle);

                Cell countryNameCell = row.createCell(3);
                countryNameCell.setCellValue(supplySale.getCountryName());
                countryNameCell.setCellStyle(centerCellStyle);

                Cell oblastOkrugNameCell = row.createCell(4);
                oblastOkrugNameCell.setCellValue(supplySale.getOblastOkrugName());
                oblastOkrugNameCell.setCellStyle(centerCellStyle);

                Cell regionNameCell = row.createCell(5);
                regionNameCell.setCellValue(supplySale.getRegionName());
                regionNameCell.setCellStyle(centerCellStyle);

                Cell supplierArticleCell = row.createCell(6);
                supplierArticleCell.setCellValue(supplySale.getSupplierArticle());
                supplierArticleCell.setCellStyle(centerCellStyle);

                Cell nmIdCell = row.createCell(7);
                nmIdCell.setCellValue(supplySale.getNmId());
                nmIdCell.setCellStyle(centerCellStyle);

                Cell barcodeCell = row.createCell(8);
                barcodeCell.setCellValue(supplySale.getBarcode());
                barcodeCell.setCellStyle(centerCellStyle);

                Cell categoryCell = row.createCell(9);
                categoryCell.setCellValue(supplySale.getCategory());
                categoryCell.setCellStyle(centerCellStyle);

                Cell subjectCell = row.createCell(10);
                subjectCell.setCellValue(supplySale.getSubject());
                subjectCell.setCellStyle(centerCellStyle);

                Cell brandCell = row.createCell(11);
                brandCell.setCellValue(supplySale.getBrand());
                brandCell.setCellStyle(centerCellStyle);

                Cell techSizeCell = row.createCell(12);
                techSizeCell.setCellValue(supplySale.getTechSize());
                techSizeCell.setCellStyle(centerCellStyle);

                Cell incomeIDCell = row.createCell(13);
                incomeIDCell.setCellValue(supplySale.getIncomeID());
                incomeIDCell.setCellStyle(centerCellStyle);

                Cell isSupplyCell = row.createCell(14);
                isSupplyCell.setCellValue(supplySale.isSupply());
                isSupplyCell.setCellStyle(centerCellStyle);

                Cell isRealizationCell = row.createCell(15);
                isRealizationCell.setCellValue(supplySale.isRealization());
                isRealizationCell.setCellStyle(centerCellStyle);

                Cell totalPriceCell = row.createCell(16);
                totalPriceCell.setCellValue(supplySale.getTotalPrice());
                totalPriceCell.setCellStyle(centerCellStyle);

                Cell discountPercentCell = row.createCell(17);
                discountPercentCell.setCellValue(supplySale.getDiscountPercent());
                discountPercentCell.setCellStyle(centerCellStyle);

                Cell sppCell = row.createCell(18);
                sppCell.setCellValue(supplySale.getSpp());
                sppCell.setCellStyle(centerCellStyle);

                Cell paymentSaleAmountCell = row.createCell(19);
                paymentSaleAmountCell.setCellValue(supplySale.getPaymentSaleAmount());
                paymentSaleAmountCell.setCellStyle(centerCellStyle);

                Cell forPayCell = row.createCell(20);
                forPayCell.setCellValue(supplySale.getForPay());
                forPayCell.setCellStyle(centerCellStyle);

                Cell finishedPriceCell = row.createCell(21);
                finishedPriceCell.setCellValue(supplySale.getFinishedPrice());
                finishedPriceCell.setCellStyle(centerCellStyle);

                Cell priceWithDiscCell = row.createCell(22);
                priceWithDiscCell.setCellValue(supplySale.getPriceWithDisc());
                priceWithDiscCell.setCellStyle(centerCellStyle);

                Cell saleIDCell = row.createCell(23);
                saleIDCell.setCellValue(supplySale.getSaleID());
                saleIDCell.setCellStyle(centerCellStyle);

                Cell orderTypeCell = row.createCell(24);
                orderTypeCell.setCellValue(supplySale.getOrderType().getDescription());
                orderTypeCell.setCellStyle(centerCellStyle);

                Cell stickerCell = row.createCell(25);
                stickerCell.setCellValue(supplySale.getSticker());
                stickerCell.setCellStyle(centerCellStyle);

                Cell gNumberCell = row.createCell(26);
                gNumberCell.setCellValue(supplySale.getGNumber());
                gNumberCell.setCellStyle(centerCellStyle);

                Cell sridCell = row.createCell(27);
                sridCell.setCellValue(supplySale.getSrid());
                sridCell.setCellStyle(centerCellStyle);
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDateFrom = dateFrom.format(formatter);
            String formattedCurrentDate = LocalDate.now().format(formatter);
            String fileName = "Статистика_Продажи_" + formattedDateFrom + "_" + formattedCurrentDate + ".xls";

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
