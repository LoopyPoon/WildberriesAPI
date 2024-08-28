package com.example.wildberriesapi.service.statistics.report;

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
public class ReportExcelExporter {
    public static void exportToExcel(List<SupplyReport> supplyReportList, File file, LocalDate dateFrom, LocalDate dateTo) {

        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Отчет по продажам");

            Row headerRow = sheet.createRow(0);

            String[] headers = {
                    "Номер отчета", "Дата начала отчета", "Дата конца отчета", "Дата формирования отчета", "Валюта отчета",
                    "Договор", "Номер строки", "Номер поставки", "Предмет", "Артикул WB",
                    "Бренд", "Артикул продавца", "Размер", "Баркод", "Тип документа",
                    "Количество", "Розничная цена", "Сумма продаж", "Согласованная скидка", "Процент комиссии",
                    "Склад", "Обоснование для оплаты", "Дата заказа", "Дата продажи", "Дата операции",
                    "Штрих-код", "Розничная цена со скидкой", "Количество доставок", "Количество возвратов", "Стоимость логистики",
                    "Тип коробов", "Согласованный продуктовый дисконт", "Промокод", "Уникальный идентификатор заказа", "Скидка постоянного покупателя",
                    "КВВ базовый", "КВВ итоговый", "Снижение КВВ из-за рейтинга", "Снижение КВВ из-за акции", "Вознаграждение с продаж",
                    "К перечислению продавцу", "Возмещение за выдачу и возврат", "Издержки по эквайрингу", "Банк-эквайер", "Вознаграждение WB",
                    "НДС с вознаграждения WB", "Номер офиса", "Офис доставки", "Номер партнера", "Партнер",
                    "ИНН партнера", "Номер таможенной декларации", "Обоснование штрафов и доплат", "Цифровой стикер", "Страна продажи",
                    "Штрафы", "Доплаты", "Возмещение издержек по перевозке", "Организатор перевозки", "Код маркировки",
                    "Стоимость хранения", "Прочие удержания/выплаты", "Стоимость платной приемки", "Уникальный идентификатор заказа", "Тип отчёта"
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
            integerCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
            integerCellStyle.setAlignment(HorizontalAlignment.CENTER);

            // Стиль для чисел с плавающей запятой
            CellStyle doubleCellStyle = workbook.createCellStyle();
            doubleCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("General"));
            doubleCellStyle.setAlignment(HorizontalAlignment.CENTER);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            // Data rows
            int rowNum = 1;

            for (SupplyReport supplyReport : supplyReportList) {
                Row row = sheet.createRow(rowNum++);

                Cell realizationReportIdCell = row.createCell(0);
                realizationReportIdCell.setCellValue(supplyReport.getRealizationReportId());
                realizationReportIdCell.setCellStyle(integerCellStyle);

                Cell dateFromCell = row.createCell(1);
                dateFromCell.setCellValue(dateFormatter.format(supplyReport.getDateFrom()));
                dateFromCell.setCellStyle(dateCellStyle);

                Cell dateToCell = row.createCell(2);
                dateToCell.setCellValue(dateFormatter.format(supplyReport.getDateTo()));
                dateToCell.setCellStyle(dateCellStyle);

                Cell createDtCell = row.createCell(3);
                createDtCell.setCellValue(dateFormatter.format(supplyReport.getCreateDt()));
                createDtCell.setCellStyle(dateCellStyle);

                Cell currencyNameCell = row.createCell(4);
                currencyNameCell.setCellValue(supplyReport.getCurrencyName());
                currencyNameCell.setCellStyle(centerCellStyle);

                Cell supplierContractCodeCell = row.createCell(5);
                supplierContractCodeCell.setCellValue(supplyReport.getSupplierContractCode());
                supplierContractCodeCell.setCellStyle(centerCellStyle);

                Cell rrdIdCell = row.createCell(6);
                rrdIdCell.setCellValue(supplyReport.getRrdId());
                rrdIdCell.setCellStyle(integerCellStyle);

                Cell giIdCell = row.createCell(7);
                giIdCell.setCellValue(supplyReport.getGiId());
                giIdCell.setCellStyle(integerCellStyle);

                Cell subjectNameCell = row.createCell(8);
                subjectNameCell.setCellValue(supplyReport.getSubjectName());
                subjectNameCell.setCellStyle(centerCellStyle);

                Cell nmIdCell = row.createCell(9);
                nmIdCell.setCellValue(supplyReport.getNmId());
                nmIdCell.setCellStyle(integerCellStyle);

                Cell brandNameCell = row.createCell(10);
                brandNameCell.setCellValue(supplyReport.getBrandName());
                brandNameCell.setCellStyle(centerCellStyle);

                Cell saNameCell = row.createCell(11);
                saNameCell.setCellValue(supplyReport.getSaName());
                saNameCell.setCellStyle(centerCellStyle);

                Cell tsNameCell = row.createCell(12);
                tsNameCell.setCellValue(supplyReport.getTsName());
                tsNameCell.setCellStyle(centerCellStyle);

                Cell barcodeCell = row.createCell(13);
                barcodeCell.setCellValue(supplyReport.getBarcode());
                barcodeCell.setCellStyle(centerCellStyle);

                Cell docTypeNameCell = row.createCell(14);
                docTypeNameCell.setCellValue(supplyReport.getDocTypeName());
                docTypeNameCell.setCellStyle(centerCellStyle);

                Cell quantityCell = row.createCell(15);
                quantityCell.setCellValue(supplyReport.getQuantity());
                quantityCell.setCellStyle(integerCellStyle);

                Cell retailPriceCell = row.createCell(16);
                retailPriceCell.setCellValue(supplyReport.getRetailPrice());
                retailPriceCell.setCellStyle(doubleCellStyle);

                Cell retailAmountCell = row.createCell(17);
                retailAmountCell.setCellValue(supplyReport.getRetailAmount());
                retailAmountCell.setCellStyle(doubleCellStyle);

                Cell salePercentCell = row.createCell(18);
                salePercentCell.setCellValue(supplyReport.getSalePercent());
                salePercentCell.setCellStyle(integerCellStyle);

                Cell commissionPercentCell = row.createCell(19);
                commissionPercentCell.setCellValue(supplyReport.getCommissionPercent());
                commissionPercentCell.setCellStyle(doubleCellStyle);

                Cell officeNameCell = row.createCell(20);
                officeNameCell.setCellValue(supplyReport.getOfficeName());
                officeNameCell.setCellStyle(centerCellStyle);

                Cell supplierOperNameCell = row.createCell(21);
                supplierOperNameCell.setCellValue(supplyReport.getSupplierOperName());
                supplierOperNameCell.setCellStyle(centerCellStyle);

                Cell orderDtCell = row.createCell(22);
                orderDtCell.setCellValue(dateFormatter.format(supplyReport.getOrderDt()));
                orderDtCell.setCellStyle(dateCellStyle);

                Cell saleDtCell = row.createCell(23);
                saleDtCell.setCellValue(dateFormatter.format(supplyReport.getSaleDt()));
                saleDtCell.setCellStyle(dateCellStyle);

                Cell rrDtCell = row.createCell(24);
                rrDtCell.setCellValue(dateFormatter.format(supplyReport.getRrDt()));
                rrDtCell.setCellStyle(dateCellStyle);

                Cell shkIdCell = row.createCell(25);
                shkIdCell.setCellValue(supplyReport.getShkId());
                shkIdCell.setCellStyle(integerCellStyle);

                Cell retailPriceWithDiscRubCell = row.createCell(26);
                retailPriceWithDiscRubCell.setCellValue(supplyReport.getRetailPriceWithDiscRub());
                retailPriceWithDiscRubCell.setCellStyle(doubleCellStyle);

                Cell deliveryAmountCell = row.createCell(27);
                deliveryAmountCell.setCellValue(supplyReport.getDeliveryAmount());
                deliveryAmountCell.setCellStyle(integerCellStyle);

                Cell returnAmountCell = row.createCell(28);
                returnAmountCell.setCellValue(supplyReport.getReturnAmount());
                returnAmountCell.setCellStyle(integerCellStyle);

                Cell deliveryRubCell = row.createCell(29);
                deliveryRubCell.setCellValue(supplyReport.getDeliveryRub());
                deliveryRubCell.setCellStyle(doubleCellStyle);

                Cell giBoxTypeNameCell = row.createCell(30);
                giBoxTypeNameCell.setCellValue(supplyReport.getGiBoxTypeName());
                giBoxTypeNameCell.setCellStyle(centerCellStyle);

                Cell productDiscountForReportCell = row.createCell(31);
                productDiscountForReportCell.setCellValue(supplyReport.getProductDiscountForReport());
                productDiscountForReportCell.setCellStyle(doubleCellStyle);

                Cell supplierPromoCell = row.createCell(32);
                supplierPromoCell.setCellValue(supplyReport.getSupplierPromo());
                supplierPromoCell.setCellStyle(doubleCellStyle);

                Cell ridCell = row.createCell(33);
                ridCell.setCellValue(supplyReport.getRid());
                ridCell.setCellStyle(integerCellStyle);

                Cell ppvzSppPrcCell = row.createCell(34);
                ppvzSppPrcCell.setCellValue(supplyReport.getPpvzSppPrc());
                ppvzSppPrcCell.setCellStyle(doubleCellStyle);

                Cell ppvzKvwPrcBaseCell = row.createCell(35);
                ppvzKvwPrcBaseCell.setCellValue(supplyReport.getPpvzKvwPrcBase());
                ppvzKvwPrcBaseCell.setCellStyle(doubleCellStyle);

                Cell ppvzKvwPrcCell = row.createCell(36);
                ppvzKvwPrcCell.setCellValue(supplyReport.getPpvzKvwPrc());
                ppvzKvwPrcCell.setCellStyle(doubleCellStyle);

                Cell supRatingPrcUpCell = row.createCell(37);
                supRatingPrcUpCell.setCellValue(supplyReport.getSupRatingPrcUp());
                supRatingPrcUpCell.setCellStyle(doubleCellStyle);

                Cell isKgvpV2Cell = row.createCell(38);
                isKgvpV2Cell.setCellValue(supplyReport.getIsKgvpV2());
                isKgvpV2Cell.setCellStyle(doubleCellStyle);

                Cell ppvzSalesCommissionCell = row.createCell(39);
                ppvzSalesCommissionCell.setCellValue(supplyReport.getPpvzSalesCommission());
                ppvzSalesCommissionCell.setCellStyle(doubleCellStyle);

                Cell ppvzForPayCell = row.createCell(40);
                ppvzForPayCell.setCellValue(supplyReport.getPpvzForPay());
                ppvzForPayCell.setCellStyle(doubleCellStyle);

                Cell ppvzRewardCell = row.createCell(41);
                ppvzRewardCell.setCellValue(supplyReport.getPpvzReward());
                ppvzRewardCell.setCellStyle(doubleCellStyle);

                Cell acquiringFeeCell = row.createCell(42);
                acquiringFeeCell.setCellValue(supplyReport.getAcquiringFee());
                acquiringFeeCell.setCellStyle(doubleCellStyle);

                Cell acquiringBankCell = row.createCell(43);
                acquiringBankCell.setCellValue(supplyReport.getAcquiringBank());
                acquiringBankCell.setCellStyle(centerCellStyle);

                Cell ppvzVwCell = row.createCell(44);
                ppvzVwCell.setCellValue(supplyReport.getPpvzVw());
                ppvzVwCell.setCellStyle(doubleCellStyle);

                Cell ppvzVwNdsCell = row.createCell(45);
                ppvzVwNdsCell.setCellValue(supplyReport.getPpvzVwNds());
                ppvzVwNdsCell.setCellStyle(doubleCellStyle);

                Cell ppvzOfficeIdCell = row.createCell(46);
                ppvzOfficeIdCell.setCellValue(supplyReport.getPpvzOfficeId());
                ppvzOfficeIdCell.setCellStyle(integerCellStyle);

                Cell ppvzOfficeNameCell = row.createCell(47);
                ppvzOfficeNameCell.setCellValue(supplyReport.getPpvzOfficeName());
                ppvzOfficeNameCell.setCellStyle(centerCellStyle);

                Cell ppvzSupplierIdCell = row.createCell(48);
                ppvzSupplierIdCell.setCellValue(supplyReport.getPpvzSupplierId());
                ppvzSupplierIdCell.setCellStyle(integerCellStyle);

                Cell ppvzSupplierNameCell = row.createCell(49);
                ppvzSupplierNameCell.setCellValue(supplyReport.getPpvzSupplierName());
                ppvzSupplierNameCell.setCellStyle(centerCellStyle);

                Cell ppvzInnCell = row.createCell(50);
                ppvzInnCell.setCellValue(supplyReport.getPpvzInn());
                ppvzInnCell.setCellStyle(centerCellStyle);

                Cell declarationNumberCell = row.createCell(51);
                declarationNumberCell.setCellValue(supplyReport.getDeclarationNumber());
                declarationNumberCell.setCellStyle(centerCellStyle);

                Cell bonusTypeNameCell = row.createCell(52);
                bonusTypeNameCell.setCellValue(supplyReport.getBonusTypeName());
                bonusTypeNameCell.setCellStyle(centerCellStyle);

                Cell stickerIdCell = row.createCell(53);
                stickerIdCell.setCellValue(supplyReport.getStickerId());
                stickerIdCell.setCellStyle(centerCellStyle);

                Cell siteCountryCell = row.createCell(54);
                siteCountryCell.setCellValue(supplyReport.getSiteCountry());
                siteCountryCell.setCellStyle(centerCellStyle);

                Cell penaltyCell = row.createCell(55);
                penaltyCell.setCellValue(supplyReport.getPenalty());
                penaltyCell.setCellStyle(doubleCellStyle);

                Cell additionalPaymentCell = row.createCell(56);
                additionalPaymentCell.setCellValue(supplyReport.getAdditionalPayment());
                additionalPaymentCell.setCellStyle(doubleCellStyle);

                Cell rebillLogisticCostCell = row.createCell(57);
                rebillLogisticCostCell.setCellValue(supplyReport.getRebillLogisticCost());
                rebillLogisticCostCell.setCellStyle(doubleCellStyle);

                Cell rebillLogisticOrgCell = row.createCell(58);
                rebillLogisticOrgCell.setCellValue(supplyReport.getRebillLogisticOrg());
                rebillLogisticOrgCell.setCellStyle(centerCellStyle);

                Cell kizCell = row.createCell(59);
                kizCell.setCellValue(supplyReport.getKiz());
                kizCell.setCellStyle(centerCellStyle);

                Cell storageFeeCell = row.createCell(60);
                storageFeeCell.setCellValue(supplyReport.getStorageFee());
                storageFeeCell.setCellStyle(doubleCellStyle);

                Cell deductionCell = row.createCell(61);
                deductionCell.setCellValue(supplyReport.getDeduction());
                deductionCell.setCellStyle(doubleCellStyle);

                Cell acceptanceCell = row.createCell(62);
                acceptanceCell.setCellValue(supplyReport.getAcceptance());
                acceptanceCell.setCellStyle(doubleCellStyle);

                Cell sridCell = row.createCell(63);
                sridCell.setCellValue(supplyReport.getSrid());
                sridCell.setCellStyle(centerCellStyle);

                Cell reportTypeCell = row.createCell(64);
                reportTypeCell.setCellValue(supplyReport.getReportType());
                reportTypeCell.setCellStyle(centerCellStyle);
            }


            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDateFrom = dateFrom.format(formatter);
            String formattedDateTo = dateTo.format(formatter);
//            String formattedCurrentDate = LocalDate.now().format(formatter);
            String fileName = "Статистика_Отчет_По_Продажам_" + formattedDateFrom + "_" + formattedDateTo + ".xls";

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
