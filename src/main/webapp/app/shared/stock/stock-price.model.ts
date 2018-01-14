import {StockModel} from "./stock.model";

export class StockPriceModel{
    public code:String;
    public stockDTO:StockModel;
    public price:number;
    public lastFetchTime:Date;
}
