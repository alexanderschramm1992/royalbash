import * as React from "react";

import "./../../common/common.css";
import "./Modal.css";

export interface ModalProps {

    readonly onValueChange: (toggled: boolean) => void;
}

interface ModalState {

    toggled: boolean,
    displayStatus: string,
    text: string,
    buttonText: string
}

export class Modal extends React.Component<ModalProps, ModalState> {

    constructor(props: ModalProps) {
        super(props);
        
        this.setState({
            toggled: false,
            displayStatus: "none",
            text: "",
            buttonText: "OK"
        });

        this.handleButtonPressed = this.handleButtonPressed.bind(this);
    }

    public isModalActive(): boolean {

        return this.state.toggled == true;
    }

    public activateModal(): void {
        
        this.setState({
            toggled: true,
            displayStatus: "block"
        });

        this.props.onValueChange(true);
    }

    public deactivateModal(): void {

        this.setState({
            toggled: false,
            displayStatus: "none"
        });

        this.props.onValueChange(false);
    }

    public setText(text: string): void {

        this.setState({
            text: text
        });
    }

    public setButtonText(buttonText: string): void {

        this.setState({
            buttonText: buttonText
        });
    }

    handleButtonPressed(): void {

        this.deactivateModal();
    }

    render(): any {

        let modalStyle = {
            display: this.state.displayStatus
        };

        return (
            <div className="modal" style={modalStyle}>
                <div className="text">{this.state.text}</div>
                <button onClick={this.handleButtonPressed}>{this.state.buttonText}</button>
            </div>
        );
    }
}
