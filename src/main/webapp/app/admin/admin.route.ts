import { Routes } from '@angular/router';

import {
    userMgmtRoute,
    userDialogRoute
} from './';

import { UserRouteAccessService } from '../shared';
import {stockDialogRoute, stockManagementRoute} from "./stock-management/stock-management.route";

const ADMIN_ROUTES = [
    ...userMgmtRoute,
    stockManagementRoute

];

export const adminState: Routes = [{
    path: '',
    data: {
        authorities: ['ROLE_ADMIN']
    },
    canActivate: [UserRouteAccessService],
    children: ADMIN_ROUTES
},
    ...userDialogRoute,...stockDialogRoute
];
