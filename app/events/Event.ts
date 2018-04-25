class Event {

    private _timestamp: Date;

    constructor() {

        this._timestamp = new Date();
    }

    public get timestamp(): Date {

        return this._timestamp;
    }
}

export default Event;
