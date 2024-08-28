package com.example.wildberriesapi.service.advance;

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
public class CostHistoryExcelExporter {
    public static void exportToExcel(List<CostHistoryData> costHistoryDataList, File file, LocalDate dateFrom, LocalDate dateTo) {

        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("История стоимости");

            Row headerRow = sheet.createRow(0);

            String[] headers = {
                    "Номер документа", "Время списания", "Сумма", "Идентификатор кампании",
                    "Название кампании", "Тип кампании", "Источник списания", "Статус кампании"
            };

            // Стиль заголовков
            CellStyle headerCellStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

            // Стиль даты и времени
            CellStyle dateTimeCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateTimeCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy HH:mm:ss"));
            dateTimeCellStyle.setAlignment(HorizontalAlignment.CENTER);

            // Стиль даты
            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
            dateCellStyle.setAlignment(HorizontalAlignment.CENTER);

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

            // Создаем заголовки
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            // Заполнение данных
            int rowNum = 1;

            for (CostHistoryData data : costHistoryDataList) {
                Row row = sheet.createRow(rowNum++);

                Cell updNumCell = row.createCell(0);
                updNumCell.setCellValue(data.getUpdNum());
                updNumCell.setCellStyle(integerCellStyle);

                Cell updTimeCell = row.createCell(1);
                if (data.getUpdTime() != null) {
                    updTimeCell.setCellValue(data.getUpdTime().toString());
                } else {
                    updTimeCell.setCellValue("Дата отсутствует");
                }
                updTimeCell.setCellStyle(centerCellStyle);

                Cell updSumCell = row.createCell(2);
                updSumCell.setCellValue(data.getUpdSum());
                updSumCell.setCellStyle(integerCellStyle);

                Cell advertIdCell = row.createCell(3);
                advertIdCell.setCellValue(data.getAdvertId());
                advertIdCell.setCellStyle(integerCellStyle);

                Cell campNameCell = row.createCell(4);
                campNameCell.setCellValue(data.getCampName());
                campNameCell.setCellStyle(centerCellStyle);

                Cell advertTypeCell = row.createCell(5);
                advertTypeCell.setCellValue(data.getAdvertType());
                advertTypeCell.setCellStyle(integerCellStyle);

                Cell paymentTypeCell = row.createCell(6);
                paymentTypeCell.setCellValue(data.getPaymentType());
                paymentTypeCell.setCellStyle(centerCellStyle);

                Cell advertStatusCell = row.createCell(7);
                advertStatusCell.setCellValue(data.getAdvertStatus());
                advertStatusCell.setCellStyle(integerCellStyle);
            }

            // Автоматическое изменение ширины колонок
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDateFrom = dateFrom.format(formatter);
            String formattedDateTo = dateTo.format(formatter);
            String fileName = "Продвижение_История_Стоимости_" + formattedDateFrom + "_" + formattedDateTo + ".xls";

            // Сохранение файла
            if (file.isDirectory()) {
                file = new File(file, fileName);
            }
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                log.info("Excel файл успешно экспортирован! Путь: {}", file.getAbsolutePath());
            }
        } catch (IOException e) {
            log.error("Ошибка экспорта в Excel", e);
        }
    }
}
