import { Injectable } from '@angular/core';
import {Http, Response} from "@angular/http";
import { SERVER_API_URL } from '../../app.constants';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

import {StockModel} from "./stock.model";
import {ResponseWrapper} from "../model/response-wrapper.model";
import {StockCommandModel} from "./stock-command.model";
import {StockPriceModel} from "./stock-price.model";

@Injectable()
export class StockService {
    private resourceUrl = SERVER_API_URL + 'api/stock';


    constructor(private http:Http) {

     }


     getStocks():Observable<StockModel[]>{
        return this.http.get(this.resourceUrl+"/allStocks").map(res => res.json());
     }

     getStockPrices():Observable<StockPriceModel[]>{
         return this.http.get(this.resourceUrl+"/prices").map(res => res.json());

     }

     getStockByCode(code:string):Observable<StockModel>{
        return this.http.get(this.resourceUrl+"/"+code).map(res => res.json());

     }

     create(stock:StockModel):Observable<ResponseWrapper>{
        stock.code = stock.code.toUpperCase();
        stock.name = stock.name.toUpperCase();
        return this.http.post(this.resourceUrl,stock).map(res => this.convertResponse(res));
     }

     update(stock:StockModel):Observable<ResponseWrapper>{
         stock.code = stock.code.toUpperCase();
         stock.name = stock.name.toUpperCase();
        return this.http.put(this.resourceUrl,stock).map(res => this.convertResponse(res));
     }

     delete(code:string):Observable<Response>{
        return this.http.delete(this.resourceUrl+"/"+code);
     }

     getUserStockInfo(login:string):Observable<any>{
        return this.http.get(this.resourceUrl+"/user/"+login).map(res => res.json());
     }

     stockCommand(command:StockCommandModel):Observable<any>{
        return this.http.post(this.resourceUrl+"/command",command).map(res => this.convertResponse(res));
     }



    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

}
