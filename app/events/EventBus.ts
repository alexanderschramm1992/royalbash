import Observer from "./Observer";
import Event from "./Event";

class EventBus<T extends Event> {

    private _observerList: Observer<T>[];
    
    constructor() {

        this._observerList = [];
    }

    public fireEvent(event: T): void {

        console.log(event);

        this._observerList.forEach(
            (observer: Observer<T>) => observer.notify(event)
        );
    }

    public subscribe(observer: Observer<T>): void {

        this._observerList.push(observer);
    }
}

export default EventBus;
