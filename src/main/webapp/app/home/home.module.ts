import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';



import { HOME_ROUTE, HomeComponent } from './';
import {CommonModule} from "@angular/common";
import { StockHistoryComponent } from './stock-history.component';
import {HISTORY_ROUTE} from "./home.route";

@NgModule({
    imports: [
        RouterModule.forChild([ HOME_ROUTE,HISTORY_ROUTE ]),
        CommonModule
    ],
    declarations: [
        HomeComponent,
        StockHistoryComponent,
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class  StockHomeModule {}
