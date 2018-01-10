import {Route, Routes} from '@angular/router';
import {StockManagementComponent} from "./stock-management.component";
import {UserDeleteDialogComponent} from "../user-management/user-management-delete-dialog.component";
import {UserDialogComponent} from "../user-management/user-management-dialog.component";
import {StockDialogOpenerComponent} from "./stock-new-dialog.component";



export const stockManagementRoute: Route = {
    path: 'stock-management',
    component: StockManagementComponent,
    data: {
        pageTitle: 'Stock Management'
    }
}

export const stockDialogRoute: Routes = [
    {
        path: 'stock-management-new',
        component: StockDialogOpenerComponent,
        outlet: 'popup'
    },
    {
        path: 'stock-management/:code/edit',
        component: StockDialogOpenerComponent,
        outlet: 'popup'
    }/**
    {
        path: 'stock-management/:login/delete',
        component: UserDeleteDialogComponent,
        outlet: 'popup'
    }**/
];

