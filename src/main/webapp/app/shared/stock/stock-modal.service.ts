import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import { User, UserService } from '../../shared';
import {StockService} from "./stock.service";
import {StockModel} from "./stock.model";

@Injectable()
export class StockModalService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private stockService: StockService
    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, code?: string | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (code) {
                this.stockService.getStockByCode(code).subscribe((stockMode) => {
                    this.ngbModalRef = this.stockModalRef(component, stockMode);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.stockModalRef(component, new StockModel());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    stockModalRef(component: Component, stock: StockModel): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.stock = stock;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
