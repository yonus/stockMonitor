import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';



/* jhipster-needle-add-admin-module-import - JHipster will add admin modules imports here */

import {
    adminState,
    UserMgmtComponent,
    UserDialogComponent,
    UserDeleteDialogComponent,
    UserMgmtDetailComponent,
    UserMgmtDialogComponent,
    UserMgmtDeleteDialogComponent,
    UserResolvePagingParams,
    UserResolve,
    UserModalService
} from './';
import {StockAppSharedModule} from "../shared";
import { StockManagementComponent } from './stock-management/stock-management.component';
import {StockDialogOpenerComponent, StockNewDialogComponent} from './stock-management/stock-new-dialog.component';

@NgModule({
    imports: [
        StockAppSharedModule,
        RouterModule.forChild(adminState),
        /* jhipster-needle-add-admin-module - JHipster will add admin modules here */
    ],
    declarations: [

        UserMgmtComponent,
        UserDialogComponent,
        UserDeleteDialogComponent,
        UserMgmtDetailComponent,
        UserMgmtDialogComponent,
        UserMgmtDeleteDialogComponent,
        StockManagementComponent,
        StockNewDialogComponent,
        StockDialogOpenerComponent
    ],
    entryComponents: [
        UserMgmtDialogComponent,
        UserMgmtDeleteDialogComponent,
        StockNewDialogComponent

    ],
    providers: [
        UserResolvePagingParams,
        UserResolve,
        UserModalService,


    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class StockAppAdminModule {}
