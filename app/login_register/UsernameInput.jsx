import React from "react";

class UsernameInput extends React.Component {

    constructor(props) {

        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {

        this.props.onUsernameChange(event.target.value);
    }

    render() {

        return (
            <fieldset>
                <legend>Username</legend>
                <input
                    type="text"
                    value={this.props.username}
                    onChange={this.handleChange}
                />
            </fieldset>
        );
    }
}

export default UsernameInput;