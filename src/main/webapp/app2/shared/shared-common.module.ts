import { NgModule, LOCALE_ID } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { registerLocaleData } from '@angular/common';
import locale from '@angular/common/locales/en';

import { WindowRef } from './tracker/window.service';
import {
    NetasWork2SharedLibsModule,
    JhiAlertComponent,
    JhiAlertErrorComponent
} from './';

@NgModule({
    imports: [
        NetasWork2SharedLibsModule
    ],
    declarations: [
        JhiAlertComponent,
        JhiAlertErrorComponent
    ],
    providers: [
        WindowRef,
        Title,
        {
            provide: LOCALE_ID,
            useValue: 'en'
        },
    ],
    exports: [
        NetasWork2SharedLibsModule,
        JhiAlertComponent,
        JhiAlertErrorComponent
    ]
})
export class NetasWork2SharedCommonModule {
    constructor() {
        registerLocaleData(locale);
    }
}
