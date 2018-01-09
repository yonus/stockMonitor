import {NgModule, CUSTOM_ELEMENTS_SCHEMA, LOCALE_ID} from '@angular/core';


import {
  AccountService,
  AuthServerProvider,
  CSRFService,
  HasAnyAuthorityDirective,
  Principal,
  StateStorageService,
  UserRouteAccessService,
    LoginModalComponent,

} from './';
import {registerLocaleData} from "@angular/common";
import {Title} from "@angular/platform-browser";

import locale from "@angular/common/locales/en";
import {StockAppSharedLibsModule} from "./shared-libs.module";
import {AppAlertComponent} from "./alert/alert.component";
import {AppAlertErrorComponent} from "./alert/alert-error.component";
import {StockService} from "./stock/stock.service";
import {LoginModalService, LoginService, UserService} from "../../app/shared";
import {StockModalService} from "./";




@NgModule({
    imports: [
           StockAppSharedLibsModule,

         ],
    declarations: [
             HasAnyAuthorityDirective,
             LoginModalComponent,
             AppAlertComponent,
             AppAlertErrorComponent
           ],
    providers: [
        AccountService,
        AuthServerProvider,
        CSRFService,
        Principal,
        StateStorageService,
        UserRouteAccessService,
        Title,
        {
            provide: LOCALE_ID,
            useValue: 'en'
        },
        LoginService,
        LoginModalService,
        UserService,
        UserRouteAccessService,
        StockService,
        StockModalService


    ],
    entryComponents: [LoginModalComponent],
    exports: [
        LoginModalComponent,
        HasAnyAuthorityDirective,
        StockAppSharedLibsModule,
        AppAlertComponent,
        AppAlertErrorComponent,

        ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class StockAppSharedModule {
    constructor() {
        registerLocaleData(locale)
    }
}
