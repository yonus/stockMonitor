//Butun componentler tarafından kullanılan modules

import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgJhipsterModule } from 'ng-jhipster';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { CookieModule } from 'ngx-cookie';

@NgModule({
    imports: [
        NgbModule.forRoot(),
        InfiniteScrollModule,
        CookieModule.forRoot(),
        NgJhipsterModule.forRoot({
            // set below to true to make alerts look like toast
            alertAsToast: false,
        })
    ],
    exports: [
        FormsModule,
        CommonModule,
        NgbModule,
        NgJhipsterModule,
        InfiniteScrollModule
    ]
})
export class StockAppSharedLibsModule {}
