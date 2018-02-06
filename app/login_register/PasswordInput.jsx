import React from "react";

class PasswordInput extends React.Component {

    constructor(props) {

        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {

        this.props.onPasswordChange(event.target.value);
    }

    render() {

        return (
            <fieldset>
                <legend>Password</legend>
                <input
                    type="password"
                    value={this.props.password}
                    onChange={this.handleChange}
                />
            </fieldset>
        );
    }
}

export default PasswordInput;
