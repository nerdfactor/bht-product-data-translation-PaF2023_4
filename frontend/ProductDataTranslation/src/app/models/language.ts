import { Currency } from "./currency";
import { Measurement } from "./measurement";
import { Translation } from "./translation";

export interface Language {
    id: number;
    name: string;
    currency: Currency;
    measurement: Measurement;
    isoCode: string;
    translations?: Translation[];
}