import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {  navbarRoute,errorRoute} from './layouts';
import {accountState} from "./account";

const LAYOUT_ROUTES = [
    navbarRoute,

    ...errorRoute
];

@NgModule({
    imports: [
        RouterModule.forRoot(LAYOUT_ROUTES, { useHash: true })
    ],
    exports: [
        RouterModule
    ]
})
export class StockAppRoutingModule {}
