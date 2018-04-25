import Event from "./Event";

interface Observer<T extends Event> {

    notify(event: T): void;
}

export default Observer;
