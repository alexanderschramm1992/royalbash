import React from "react";
import UsernameInput from "./UsernameInput.jsx";
import PasswordInput from "./PasswordInput.jsx";

class Login extends React.Component {

    constructor(props) {

        super(props);
        this.state = {
            username: "",
            password: "",
        };

        this.onUsernameChange = this.onUsernameChange.bind(this);
        this.onPasswordChange = this.onPasswordChange.bind(this);
        this.handleLoginClick = this.handleLoginClick.bind(this);
    }

    onUsernameChange(username) {

        this.setState({
            username: username
        });
    }

    onPasswordChange(password) {

        this.setState({
            password: password
        });
    }

    handleLoginClick() {
        console.log("Execute Login with");
        console.log(this.state.username);
        console.log(this.state.password);
    }

    render() {

        return (
            <div className="login-form">
                <UsernameInput
                    username={this.state.username}
                    onUsernameChange={this.onUsernameChange}
                />
                <PasswordInput
                    password={this.state.password}
                    onPasswordChange={this.onPasswordChange}
                />
                <button onClick={this.handleLoginClick}>Login</button>
                <p>
                    Not registered yet?
                    <a href="register.html">Register</a>
                </p>
            </div>
        );
    }
}

export default Login;
