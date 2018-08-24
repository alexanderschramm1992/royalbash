import store from "../Store";
import {clearInterval} from "timers";
import {LoadGameIssuedActionFactory} from "../actions/loadinggame/LoadGameIssuedAction";
import {PollingGameActivatedActionFactory} from "../actions/loadinggame/PollingGameActivatedAction";
import {PollingGameDeactivatedActionFactory} from "../actions/loadinggame/PollingGameDeactivatedAction";
import Timer = NodeJS.Timer;

class PollingController {

    private interval: any;

    constructor () {

        store.subscribe((): void => {

            if(!store.getState().ownTurn && !store.getState().pollingGame) {

                this.activateInterval();
                store.dispatch(PollingGameActivatedActionFactory.getInstance());
            } else if(store.getState().ownTurn && store.getState().pollingGame) {

                this.deactivateInterval();
                store.dispatch(PollingGameDeactivatedActionFactory.getInstance());
            }
        });
    }

    private activateInterval(): void {

        this.interval = setInterval((): void => {
            store.dispatch(LoadGameIssuedActionFactory.getInstance(
                store.getState().gameId,
                store.getState().playerId

            ));
        }, 1000);
    }

    private deactivateInterval(): void {
        clearInterval(this.interval);
    }
}

export default PollingController;
