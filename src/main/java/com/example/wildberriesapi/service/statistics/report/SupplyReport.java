package com.example.wildberriesapi.service.statistics.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SupplyReport {
    // Номер отчета
    @JsonProperty("realizationreport_id")
    private int realizationReportId;

    // Дата начала отчета
    @JsonProperty("date_from")
    private LocalDate dateFrom;

    // Дата конца отчета
    @JsonProperty("date_to")
    private LocalDate dateTo;

    // Дата формирования отчета
    @JsonProperty("create_dt")
    private LocalDate createDt;

    // Валюта отчета
    @JsonProperty("currency_name")
    private String currencyName;

    // Договор
    @JsonProperty("suppliercontract_code")
    private String supplierContractCode;

    // Номер строки
    @JsonProperty("rrd_id")
    private long rrdId;

    // Номер поставки
    @JsonProperty("gi_id")
    private long giId;

    // Предмет
    @JsonProperty("subject_name")
    private String subjectName;

    // Артикул WB
    @JsonProperty("nm_id")
    private long nmId;

    // Бренд
    @JsonProperty("brand_name")
    private String brandName;

    // Артикул продавца
    @JsonProperty("sa_name")
    private String saName;

    // Размер
    @JsonProperty("ts_name")
    private String tsName;

    // Баркод
    @JsonProperty("barcode")
    private String barcode;

    // Тип документа
    @JsonProperty("doc_type_name")
    private String docTypeName;

    // Количество
    @JsonProperty("quantity")
    private int quantity;

    // Розничная цена
    @JsonProperty("retail_price")
    private double retailPrice;

    // Сумма продаж (возвратов)
    @JsonProperty("retail_amount")
    private double retailAmount;

    // Согласованная скидка
    @JsonProperty("sale_percent")
    private int salePercent;

    // Процент комиссии
    @JsonProperty("commission_percent")
    private double commissionPercent;

    // Склад
    @JsonProperty("office_name")
    private String officeName;

    // Обоснование для оплаты
    @JsonProperty("supplier_oper_name")
    private String supplierOperName;

    // Дата заказа
    @JsonProperty("order_dt")
    private LocalDate orderDt;

    // Дата продажи
    @JsonProperty("sale_dt")
    private LocalDate saleDt;

    // Дата операции
    @JsonProperty("rr_dt")
    private LocalDate rrDt;

    // Штрих-код
    @JsonProperty("shk_id")
    private long shkId;

    // Розничная цена со скидкой
    @JsonProperty("retail_price_withdisc_rub")
    private double retailPriceWithDiscRub;

    // Количество доставок
    @JsonProperty("delivery_amount")
    private int deliveryAmount;

    // Количество возвратов
    @JsonProperty("return_amount")
    private int returnAmount;

    // Стоимость логистики
    @JsonProperty("delivery_rub")
    private double deliveryRub;

    // Тип коробов
    @JsonProperty("gi_box_type_name")
    private String giBoxTypeName;

    // Согласованный продуктовый дисконт
    @JsonProperty("product_discount_for_report")
    private double productDiscountForReport;

    // Промокод
    @JsonProperty("supplier_promo")
    private double supplierPromo;

    // Уникальный идентификатор заказа
    @JsonProperty("rid")
    private long rid;

    // Скидка постоянного покупателя
    @JsonProperty("ppvz_spp_prc")
    private double ppvzSppPrc;

    // Размер кВВ без НДС, % базовый
    @JsonProperty("ppvz_kvw_prc_base")
    private double ppvzKvwPrcBase;

    // Итоговый кВВ без НДС, %
    @JsonProperty("ppvz_kvw_prc")
    private double ppvzKvwPrc;

    // Размер снижения кВВ из-за рейтинга
    @JsonProperty("sup_rating_prc_up")
    private double supRatingPrcUp;

    // Размер снижения кВВ из-за акции
    @JsonProperty("is_kgvp_v2")
    private double isKgvpV2;

    // Вознаграждение с продаж до вычета услуг поверенного, без НДС
    @JsonProperty("ppvz_sales_commission")
    private double ppvzSalesCommission;

    // К перечислению продавцу за реализованный товар
    @JsonProperty("ppvz_for_pay")
    private double ppvzForPay;

    // Возмещение за выдачу и возврат товаров на ПВЗ
    @JsonProperty("ppvz_reward")
    private double ppvzReward;

    // Возмещение издержек по эквайрингу
    @JsonProperty("acquiring_fee")
    private double acquiringFee;

    // Наименование банка-эквайера
    @JsonProperty("acquiring_bank")
    private String acquiringBank;

    // Вознаграждение WB без НДС
    @JsonProperty("ppvz_vw")
    private double ppvzVw;

    // НДС с вознаграждения WB
    @JsonProperty("ppvz_vw_nds")
    private double ppvzVwNds;

    // Номер офиса
    @JsonProperty("ppvz_office_id")
    private long ppvzOfficeId;

    // Наименование офиса доставки
    @JsonProperty("ppvz_office_name")
    private String ppvzOfficeName;

    // Номер партнера
    @JsonProperty("ppvz_supplier_id")
    private long ppvzSupplierId;

    // Партнер
    @JsonProperty("ppvz_supplier_name")
    private String ppvzSupplierName;

    // ИНН партнера
    @JsonProperty("ppvz_inn")
    private String ppvzInn;

    // Номер таможенной декларации
    @JsonProperty("declaration_number")
    private String declarationNumber;

    // Обоснование штрафов и доплат
    @JsonProperty("bonus_type_name")
    private String bonusTypeName;

    // Цифровой стикер
    @JsonProperty("sticker_id")
    private String stickerId;

    // Страна продажи
    @JsonProperty("site_country")
    private String siteCountry;

    // Штрафы
    @JsonProperty("penalty")
    private double penalty;

    // Доплаты
    @JsonProperty("additional_payment")
    private double additionalPayment;

    // Возмещение издержек по перевозке
    @JsonProperty("rebill_logistic_cost")
    private double rebillLogisticCost;

    // Организатор перевозки
    @JsonProperty("rebill_logistic_org")
    private String rebillLogisticOrg;

    // Код маркировки
    @JsonProperty("kiz")
    private String kiz;

    // Стоимость хранения
    @JsonProperty("storage_fee")
    private double storageFee;

    // Прочие удержания/выплаты
    @JsonProperty("deduction")
    private double deduction;

    // Стоимость платной приёмки
    @JsonProperty("acceptance")
    private double acceptance;

    // Уникальный идентификатор заказа
    @JsonProperty("srid")
    private String srid;

    // Тип отчёта
    @JsonProperty("report_type")
    private int reportType;
}
