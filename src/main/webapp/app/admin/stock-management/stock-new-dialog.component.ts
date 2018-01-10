import {Component, OnDestroy, OnInit} from '@angular/core';
import {StockModalService, StockModel, StockService} from "../../shared";
import {UserModalService} from "../user-management/user-modal.service";
import {UserMgmtDialogComponent} from "../user-management/user-management-dialog.component";
import {ActivatedRoute} from "@angular/router";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {JhiAlertService, JhiEventManager} from "ng-jhipster";

@Component({
  selector: 'stock-new-dialog',
  templateUrl: './stock-new-dialog.component.html',
  styles: []
})
export class StockNewDialogComponent implements OnInit {
    stock:StockModel;
    isSaving:Boolean
  constructor(public activeModal: NgbActiveModal,
              public stockService:StockService,
              private eventManager: JhiEventManager,
              private alertService: JhiAlertService
  ) {

  }



    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.stock.id) {
            this.stockService.update(this.stock).subscribe((response) => this.onSaveSuccess(response), (result) => this.onSaveError(result));
        } else {

            this.stockService.create(this.stock).subscribe((response) => this.onSaveSuccess(response), (result) => this.onSaveError(result));
        }
    }

    private onSaveSuccess(result) {
        this.eventManager.broadcast({ name: 'stockAddedOrEdited', content: 'OK' });
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(result) {
        this.isSaving = false;
        if(result) {
            let response = result.json();
            this.alertService.error(response.title);
            console.log(result);
            this.activeModal.dismiss(result)
        }

    }

}


@Component({
    selector: 'stock-dialog-opener',
    template: ''
})
export class StockDialogOpenerComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private stockModalService: StockModalService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['code'] ) {
                this.stockModalService.open(StockNewDialogComponent as Component, params['code']);
            } else {
                this.stockModalService.open(StockNewDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
