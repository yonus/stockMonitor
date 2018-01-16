import { Component, OnInit } from '@angular/core';
import {StockHistoryModel, StockModel, StockService} from "../shared";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'stock-history',
  templateUrl: './stock-history.component.html',
  styles: []
})
export class StockHistoryComponent implements OnInit {

  stockHistories:StockHistoryModel[];
  stock:StockModel;
  private routeSub;

  constructor(private stockService:StockService,
              private route:ActivatedRoute) {

  }

  ngOnInit() {
      this.routeSub = this.route.params.subscribe(params =>{
          let code = params["code"];
          this.stockService.getStockByCode(code).subscribe(s => {this.stock = s});
          this.stockService.getStockHistory(code).subscribe(history => {history = this.stockHistories});
      })
  }

  ngOnDestroy(){
      this.routeSub.unsubscribe();
  }

}
