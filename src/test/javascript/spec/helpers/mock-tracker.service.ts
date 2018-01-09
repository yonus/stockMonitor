import { SpyObject } from './spyobject';
import { JhiTrackerService } from '../../../../main/webapp/app2/shared/tracker/tracker.service';

export class MockTrackerService extends SpyObject {

    constructor() {
        super(JhiTrackerService);
    }

    connect() {}
}
