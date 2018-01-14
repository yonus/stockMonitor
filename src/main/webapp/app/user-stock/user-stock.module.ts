import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';



import { USER_STOCK_ROUTE,UserStockComponent } from './';
import {StockAppSharedModule} from "../shared";
import {StockTradeCommandCompoment} from "./stock-trade-command.component";

@NgModule({
    imports: [
        StockAppSharedModule,
        RouterModule.forChild([ USER_STOCK_ROUTE ])
    ],
    declarations: [
        UserStockComponent,
        StockTradeCommandCompoment
    ],
    entryComponents: [
        StockTradeCommandCompoment
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class  UserStockModule {}
