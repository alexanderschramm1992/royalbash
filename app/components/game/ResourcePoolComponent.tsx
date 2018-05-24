import * as React from "react";

import "./../common/common.css";
import "./ResourcePoolComponent.css";
import {ResourcePool} from "../../model/Game";
import store, {getPlayer} from "../../Store";

export class ResourcePoolComponent extends React.Component<{}, ResourcePool> {

    constructor(props: any) {
        super(props);

        store.subscribe((): void => {

            let player = getPlayer();
            this.setState({

                rations: player.resourcePool.rations,
                material: player.resourcePool.material,
                blessing: player.resourcePool.blessing
            });
        });
    }

    render(): any {

        return (
            <div className="resource-pool">
                <div className="resource-display resource-display-rations">
                    <div className="resource-meter resource-meter-rations"/>
                    <div className="resource-counter resource-counter-rations">
                        {this.state.rations}
                    </div>
                </div>
                <div className="resource-display resource-display-material">
                    <div className="resource-meter resource-meter-material"/>
                    <div className="resource-counter resource-counter-material">
                        {this.state.material}
                    </div>
                </div>
                <div className="resource-display resource-display-blessing">
                    <div className="resource-meter resource-meter-blessing"/>
                    <div className="resource-counter resource-counter-blessing">
                        {this.state.blessing}
                    </div>
                </div>
            </div>
        )
    }
}