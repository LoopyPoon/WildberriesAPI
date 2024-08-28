package com.example.wildberriesapi.service.analytics.paid_storage;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class PaidStorageExcelExporter {
    public static void exportToExcel(List<PaidStorageReport> paidStorageReports, File file, LocalDate dateFrom, LocalDate dateTo) {

        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Отчет по платному хранению");

            Row headerRow = sheet.createRow(0);

            String[] headers = {
                    "Дата расчета", "Коэффициент логистики и хранения", "ID склада", "Название склада",
                    "Коэффициент склада", "ID поставки", "ID размера артикула", "Размер",
                    "Баркод", "Предмет", "Бренд", "Артикул продавца", "Артикул WB", "Объём товара",
                    "Способ расчёта", "Сумма хранения", "Количество товара подлежащих тарифицированию",
                    "Код паллетоместа", "Количество паллет", "Дата первоначального расчёта",
                    "Скидка программы лояльности", "Дата фиксации тарифа", "Дата понижения тарифа"
            };

            // Стиль заголовков
            CellStyle headerCellStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

            // Стиль для строк
            CellStyle centerCellStyle = workbook.createCellStyle();
            centerCellStyle.setAlignment(HorizontalAlignment.CENTER);

            // Стиль для целых чисел
            CellStyle integerCellStyle = workbook.createCellStyle();
            integerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            integerCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));

            // Стиль для чисел с плавающей запятой
            CellStyle doubleCellStyle = workbook.createCellStyle();
            doubleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            doubleCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("General"));

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Data rows
            int rowNum = 1;

            for (PaidStorageReport report : paidStorageReports) {
                Row row = sheet.createRow(rowNum++);

                Cell dateCell = row.createCell(0);
                dateCell.setCellValue(report.getDate());
                dateCell.setCellStyle(centerCellStyle);

                Cell logWarehouseCoefCell = row.createCell(1);
                logWarehouseCoefCell.setCellValue(report.getLogWarehouseCoef());
                logWarehouseCoefCell.setCellStyle(doubleCellStyle);

                Cell officeIdCell = row.createCell(2);
                officeIdCell.setCellValue(report.getOfficeId());
                officeIdCell.setCellStyle(integerCellStyle);

                Cell warehouseCell = row.createCell(3);
                warehouseCell.setCellValue(report.getWarehouse());
                warehouseCell.setCellStyle(centerCellStyle);

                Cell warehouseCoefCell = row.createCell(4);
                warehouseCoefCell.setCellValue(report.getWarehouseCoef());
                warehouseCoefCell.setCellStyle(doubleCellStyle);

                Cell giIdCell = row.createCell(5);
                giIdCell.setCellValue(report.getGiId());
                giIdCell.setCellStyle(integerCellStyle);

                Cell chrtIdCell = row.createCell(6);
                chrtIdCell.setCellValue(report.getChrtId());
                chrtIdCell.setCellStyle(integerCellStyle);

                Cell sizeCell = row.createCell(7);
                sizeCell.setCellValue(report.getSize());
                sizeCell.setCellStyle(centerCellStyle);

                Cell barcodeCell = row.createCell(8);
                barcodeCell.setCellValue(report.getBarcode());
                barcodeCell.setCellStyle(centerCellStyle);

                Cell subjectCell = row.createCell(9);
                subjectCell.setCellValue(report.getSubject());
                subjectCell.setCellStyle(centerCellStyle);

                Cell brandCell = row.createCell(10);
                brandCell.setCellValue(report.getBrand());
                brandCell.setCellStyle(centerCellStyle);

                Cell vendorCodeCell = row.createCell(11);
                vendorCodeCell.setCellValue(report.getVendorCode());
                vendorCodeCell.setCellStyle(centerCellStyle);

                Cell nmIdCell = row.createCell(12);
                nmIdCell.setCellValue(report.getNmId());
                nmIdCell.setCellStyle(integerCellStyle);

                Cell volumeCell = row.createCell(13);
                volumeCell.setCellValue(report.getVolume());
                volumeCell.setCellStyle(doubleCellStyle);

                Cell calcTypeCell = row.createCell(14);
                calcTypeCell.setCellValue(report.getCalcType());
                calcTypeCell.setCellStyle(centerCellStyle);

                Cell warehousePriceCell = row.createCell(15);
                warehousePriceCell.setCellValue(report.getWarehousePrice());
                warehousePriceCell.setCellStyle(doubleCellStyle);

                Cell barcodesCountCell = row.createCell(16);
                barcodesCountCell.setCellValue(report.getBarcodesCount());
                barcodesCountCell.setCellStyle(integerCellStyle);

                Cell palletPlaceCodeCell = row.createCell(17);
                palletPlaceCodeCell.setCellValue(report.getPalletPlaceCode());
                palletPlaceCodeCell.setCellStyle(integerCellStyle);

                Cell palletCountCell = row.createCell(18);
                palletCountCell.setCellValue(report.getPalletCount());
                palletCountCell.setCellStyle(doubleCellStyle);

                Cell originalDateCell = row.createCell(19);
                originalDateCell.setCellValue(report.getOriginalDate());
                originalDateCell.setCellStyle(centerCellStyle);

                Cell loyaltyDiscountCell = row.createCell(20);
                loyaltyDiscountCell.setCellValue(report.getLoyaltyDiscount());
                loyaltyDiscountCell.setCellStyle(doubleCellStyle);

                Cell tariffFixDateCell = row.createCell(21);
                tariffFixDateCell.setCellValue(report.getTariffFixDate());
                tariffFixDateCell.setCellStyle(centerCellStyle);

                Cell tariffLowerDateCell = row.createCell(22);
                tariffLowerDateCell.setCellValue(report.getTariffLowerDate());
                tariffLowerDateCell.setCellStyle(centerCellStyle);
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDateFrom = dateFrom.format(formatter);
            String formattedDateTo = dateTo.format(formatter);
//            String formattedCurrentDate = LocalDate.now().format(formatter);
            String fileName = "Аналитика_Отчет_По_Платному_Хранению_" + formattedDateFrom + "_" + formattedDateTo + ".xls";

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
