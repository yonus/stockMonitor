import {Route} from "@angular/router";
import {UserStockComponent} from "./user-stock.component";



export  const  USER_STOCK_ROUTE : Route = {
        path: 'user-stock/:login',
        component: UserStockComponent,
        data: {
        authorities: [],
            pageTitle: 'Welcome, Stock Monitor!'
        }
};
