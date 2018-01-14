import {Component, OnInit} from "@angular/core";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {ResponseWrapper, StockCommandModel, StockModel, StockService} from "../shared";
import {COMMAND_BUY, COMMAND_SELL} from "./user-stock.constant";
import {JhiEventManager} from "ng-jhipster";

@Component({
    selector: 'stock-trade-command',
    templateUrl: './stock-trade-command.component.html',
    styleUrls: ['user-stock.css']
})
export class StockTradeCommandCompoment implements  OnInit{
    private command;
    private userStockInfo;
    private allStocks:StockModel[]
    private feasibleStock:StockModel[];
    private selectedStock:StockModel;
    private isBuyButtonHidden = false;
    private isSellButtonHidden = false;
    private stockCommand:StockCommandModel;
    private login:String;

    constructor(private activeModal: NgbActiveModal,
                private stockService:StockService,
                private eventManager:JhiEventManager){

    }
    ngOnInit(): void {
        this.selectedStock = new StockModel();
        this.stockCommand = new StockCommandModel();
        if(this.command == COMMAND_SELL){
            this.prepareFeasibleStockForSell();
        }else if(this.command == COMMAND_BUY){
            this.prepareFeasibleStockForBuy();
        }
        this.prepareButtonsVisible()
    }

    prepareFeasibleStockForSell(){
        this.feasibleStock = [];
        if(this.userStockInfo && this.userStockInfo.stockCountAndPriceDTOS){
            this.userStockInfo.stockCountAndPriceDTOS.forEach(stockCountAndPriceDTO =>{
                let stockModel =  new StockModel();
                stockModel.code = stockCountAndPriceDTO.stockDTO.code;
                stockModel.name = stockCountAndPriceDTO.stockDTO.name;
                this.feasibleStock.push(stockModel);
            });
            this.selectedStock = this.feasibleStock[0];

        }
    }

    prepareFeasibleStockForBuy():void {
        this.feasibleStock = this.allStocks;
        this.selectedStock = this.feasibleStock[0];
    }
    byStockCode(item1: StockModel, item2: StockModel):boolean {
        if(item1 && item2) {
            return item1.code === item2.code;
        }
        return false;
    }

    prepareButtonsVisible():void{
        if(this.command == COMMAND_BUY){
            this.isSellButtonHidden = true;
        }else {
            this.isBuyButtonHidden = true;
        }
    }

    clear():void{
        this.activeModal.dismiss("cancel")
    }

    save():void{
         this.stockCommand.login = this.login;
         this.stockCommand.command = this.command;
         this.stockCommand.code = this.selectedStock.code;
         this.stockService.stockCommand(this.stockCommand).subscribe((res) =>this.commandSuccessHandler(res.json),
             (res) => this.commandFailHandler(res.json()));
    }

    commandSuccessHandler(result):void{
        alert("Your command is successfull");
        this.eventManager.broadcast({ name: 'userStockUpdated', content: 'OK' });
        this.activeModal.dismiss("command success");

    }

    commandFailHandler(result):void{
        alert("Your command is failed: "+result.title);
    }



}
