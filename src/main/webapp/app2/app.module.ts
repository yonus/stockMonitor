import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ngx-webstorage';

import { NetasWork2SharedModule, UserRouteAccessService } from './shared';
import { NetasWork2AppRoutingModule} from './app-routing.module';
import { NetasWork2HomeModule } from './home/home.module';
import { NetasWork2AdminModule } from './admin/admin.module';
import { NetasWork2AccountModule } from './account/account.module';
import { NetasWork2EntityModule } from './entities/entity.module';
import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

// jhipster-needle-angular-add-module-import JHipster will add new module here

import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ErrorComponent
} from './layouts';

@NgModule({
    imports: [
        BrowserModule,
        NetasWork2AppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        NetasWork2SharedModule,
        NetasWork2HomeModule,
        NetasWork2AdminModule,
        NetasWork2AccountModule,
        NetasWork2EntityModule,
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        FooterComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [ JhiMainComponent ]
})
export class NetasWork2AppModule {}
