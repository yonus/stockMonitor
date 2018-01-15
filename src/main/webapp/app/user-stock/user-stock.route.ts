import {Route} from "@angular/router";
import {UserStockComponent} from "./user-stock.component";
import {UserRouteAccessService} from "../shared/auth/user-route-access-service";



export  const  USER_STOCK_ROUTE : Route = {
        path: 'user-stock/:login',
        component: UserStockComponent,
        data: {
        authorities: ["ROLE_USER"],
            pageTitle: 'Welcome, Stock Monitor!'
        },
        canActivate:[UserRouteAccessService]
};
