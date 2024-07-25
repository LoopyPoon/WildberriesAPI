/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { SupplyIncomes } from '../models/SupplyIncomes';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class DefaultService {
    /**
     * Поставки
     * Поставки. Максимум 1 запрос в минуту
     * @param dateFrom Дата и время последнего изменения по поставке. Дата в формате RFC3339. Можно передать дату или дату со временем. Время можно указывать с точностью до секунд или миллисекунд. Время передаётся в часовом поясе Мск (UTC+3).
     * @returns SupplyIncomes Список поставок
     * @throws ApiError
     */
    public static getStatiscticsIncomes(
        dateFrom?: string,
    ): CancelablePromise<SupplyIncomes> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/statisctics/incomes/',
            query: {
                'dateFrom': dateFrom,
            },
        });
    }
}
