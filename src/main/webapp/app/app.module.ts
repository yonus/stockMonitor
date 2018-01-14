import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ngx-webstorage';
import { StockHomeModule} from './home/home.module';

import {ErrorComponent, NavbarComponent, StockMainComponent} from './layouts'
import {StockAppRoutingModule} from "./app-routing.module";
import {LoginModalService, LoginService, StockAppSharedModule} from "./shared";
import {UserRouteAccessService} from "../app/shared";
import {StockAccountModule} from "./account/account.module";
import {customHttpProvider} from "./blocks/interceptor/http.provider";
import {StockService} from "./shared/stock/stock.service";
import {StockAppAdminModule} from "./admin/admin.module";
import {UserStockModule} from "./user-stock";





@NgModule({
    imports: [
        BrowserModule,
        StockAppRoutingModule,
        StockAppSharedModule,
        StockHomeModule,
        StockAccountModule,
        StockAppAdminModule,
        UserStockModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
    ],
    declarations: [ // add compoment, directivei pipe that is used for this module
          StockMainComponent,
          ErrorComponent,
          NavbarComponent

    ],
    providers: [
        customHttpProvider(),


    ],
    bootstrap: [StockMainComponent]
})
export class StockAppModule {}
