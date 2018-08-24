import * as React from "react";

import "../../common/common.css";
import "./ResourcePoolComponent.css";
import {ResourcePool} from "../../../model/Game";
import store, {getPlayer} from "../../../Store";
import { ValueMeter, ValueMeterColor } from "./ValueMeter";

export class ResourcePoolComponent extends React.Component<{}, ResourcePool> {

    constructor(props: any) {
        super(props);

        this.state = {
            rations: 0,
            material: 0,
            blessing: 0
        };

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
                    <ValueMeter
                        resource={this.state.rations}
                        maxResource={store.getState().constants.maxRations}
                        color={ValueMeterColor.green}
                    />
                    <div className="resource-counter resource-counter-rations font-size-large">
                        {this.state.rations}
                    </div>
                </div>
                <div className="resource-display resource-display-material">
                    <ValueMeter
                        resource={this.state.material}
                        maxResource={store.getState().constants.maxMaterial}
                        color={ValueMeterColor.silver}
                    />
                    <div className="resource-counter resource-counter-material font-size-large">
                        {this.state.material}
                    </div>
                </div>
                <div className="resource-display resource-display-blessing">
                    <ValueMeter
                        resource={this.state.blessing}
                        maxResource={store.getState().constants.maxBlessing}
                        color={ValueMeterColor.violet}
                    />
                    <div className="resource-counter resource-counter-blessing font-size-large">
                        {this.state.blessing}
                    </div>
                </div>
            </div>
        )
    }
}