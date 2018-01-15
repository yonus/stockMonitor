import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRouteSnapshot, NavigationEnd } from '@angular/router';

import { Title } from '@angular/platform-browser';
import {JhiEventManager} from "ng-jhipster";
import {Principal, StockPriceChangeTrackerService} from "../../shared";

@Component({
    selector: 'stock-main',
    templateUrl: './main.component.html'
})
export class StockMainComponent implements OnInit {

    constructor(
        private titleService: Title,
        private router: Router,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private stockPriceTrackerService:StockPriceChangeTrackerService
    ) {}

    private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {
        let title: string = (routeSnapshot.data && routeSnapshot.data['pageTitle']) ? routeSnapshot.data['pageTitle'] : 'netasWork2App';
        if (routeSnapshot.firstChild) {
            title = this.getPageTitle(routeSnapshot.firstChild) || title;
        }
        return title;
    }

    ngOnInit() {
        this.router.events.subscribe((event) => {
            if (event instanceof NavigationEnd) {
                this.titleService.setTitle(this.getPageTitle(this.router.routerState.snapshot.root));
            }
        });
        this.eventManager.subscribe("authenticationSuccess",(content)=>{
            this.principal.identity().then((account) => {
                if(this.principal.hasAnyAuthorityDirect(['ROLE_USER'])){
                    this.router.navigate(['/user-stock',account.login]);
                }else if(this.principal.hasAnyAuthorityDirect(['ROLE_ADMIN'])){
                    this.router.navigate(['/stock-management']);
                }
            });
        });

        this.registerPriceTracker();
    }


    private registerPriceTracker(){
        this.stockPriceTrackerService.connect();
        this.stockPriceTrackerService.subscribe();
        this.stockPriceTrackerService.receive().subscribe((data)=>{
            this.eventManager.broadcast({name:"priceChangeEvent", content:data})
        });
    }
}
