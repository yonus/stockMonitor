import {Injectable} from "@angular/core";
import {AuthServerProvider, CSRFService} from "../";
import {Router} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {Observer} from "rxjs/Observer";
import {Observable} from "rxjs/Observable";

import * as SockJS from 'sockjs-client';
import * as Stomp from 'webstomp-client';

@Injectable()
export class StockPriceChangeTrackerService{


    stompClient = null;
    subscriber = null;
    connection: Promise<any>;
    connectedPromise: any;
    listener: Observable<any>;
    listenerObserver: Observer<any>;
    alreadyConnectedOnce = false;
    private subscription: Subscription;

    constructor(private  authServerProvider:AuthServerProvider,
                private router: Router,
                private csrfService: CSRFService
    ){
        this.connection = this.createConnection();
        this.listener = this.createListener();

    }

    connect() {
        if (this.connectedPromise === null) {
            this.connection = this.createConnection();
        }
        // building absolute path so that websocket doesn't fail when deploying with a context path
        const loc = window.location;
        let url;
        url = '//' + loc.host + loc.pathname + 'websocket/tracker';
        const authToken = this.authServerProvider.getToken();
        if (authToken) {
            url += '?access_token=' + authToken;
        }
        const socket = new SockJS(url);
        this.stompClient = Stomp.over(socket);
        const headers = {};
        this.stompClient.connect(headers, () => {
            this.connectedPromise('success');
            this.connectedPromise = null
            if (!this.alreadyConnectedOnce) {
                this.subscription = this.router.events.subscribe((event) => {

                });
                this.alreadyConnectedOnce = true;
            }
        });
    }



    disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
            this.stompClient = null;
        }
        if (this.subscription) {
            this.subscription.unsubscribe();
            this.subscription = null;
        }
        this.alreadyConnectedOnce = false;
    }

    receive() {
        return this.listener;
    }

    subscribe() {
        this.connection.then(() => {
            this.subscriber = this.stompClient.subscribe('/topic/prices', (data) => {
                this.listenerObserver.next(JSON.parse(data.body));
            });
        });
    }

    unsubscribe() {
        if (this.subscriber !== null) {
            this.subscriber.unsubscribe();
        }
        this.listener = this.createListener();
    }


    private createListener(): Observable<any> {
        return new Observable((observer) => {
            this.listenerObserver = observer;
        });
    }

    private createConnection(): Promise<any> {
        return new Promise((resolve, reject) => this.connectedPromise = resolve);
    }

}
