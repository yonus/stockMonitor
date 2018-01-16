import { Route } from '@angular/router';

import { HomeComponent } from './';
import {StockHistoryComponent} from "./stock-history.component";

export const HOME_ROUTE: Route = {
    path: '',
    component: HomeComponent,
    data: {
        authorities: [],
        pageTitle: 'Welcome, Stock Monitor!'
    }
};


export const HISTORY_ROUTE: Route = {
    path: 'history/:code',
    component: StockHistoryComponent,
    data: {
        authorities: [],
        pageTitle: 'Welcome, Stock Monitor!'
    }
};

