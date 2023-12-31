import { Product } from "./product";

export interface Picture {
    id: number;
    filename: string;
    format: string;
    height: number;
    width: number;
    product: Product;
}