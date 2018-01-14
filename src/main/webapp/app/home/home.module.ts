import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';



import { HOME_ROUTE, HomeComponent } from './';
import {CommonModule} from "@angular/common";

@NgModule({
    imports: [
        RouterModule.forChild([ HOME_ROUTE ]),
        CommonModule
    ],
    declarations: [
        HomeComponent,
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class  StockHomeModule {}
