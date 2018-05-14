import * as React from "react";

import "./../../common/common.css";
import "./GraveyardComponent.css";

export class DeckComponent extends React.Component<{}, {}> {

    constructor(props: any) {
        super(props);
    }


    render(): any {

        return (
            <div className="graveyard">
                <div className="stack border-large border-radius">
                </div>
            </div>
        );
    }
}

export default DeckComponent;
