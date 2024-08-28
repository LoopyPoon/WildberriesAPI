/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export type SupplyIncomes = {
    /**
     * Номер поставки
     */
    incomeId?: number;
    /**
     * Номер УПД
     */
    number?: string;
    /**
     * Дата поступления. Если часовой пояс не указан, то берется Московское время UTC+3.
     */
    date?: string;
    /**
     * Дата и время обновления информации в сервисе. Это поле соответствует параметру `dateFrom` в запросе. Если часовой пояс не указан, то берется Московское время UTC+3.
     */
    lastChangeDate?: string;
    /**
     * Артикул продавца
     */
    supplierArticle?: string;
    /**
     * Размер товара (пример S, M, L, XL, 42, 42-43)
     */
    techSize?: string;
    /**
     * Бар-код
     */
    barcode?: string;
    /**
     * Количество
     */
    quantity?: number;
    /**
     * Цена из УПД
     */
    totalPrice?: number;
    /**
     * Дата принятия (закрытия) в WB. Если часовой пояс не указан, то берется Московское время UTC+3
     */
    dateClose?: string;
    /**
     * Название склада
     */
    warehouseName?: string;
    /**
     * Артикул WB
     */
    nmId?: number;
    /**
     * Текущий статус поставки
     */
    status?: SupplyIncomes.status;
};
export namespace SupplyIncomes {
    /**
     * Текущий статус поставки
     */
    export enum status {
        _ = 'Принято',
    }
}

