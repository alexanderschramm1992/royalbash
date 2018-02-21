import React from "react";
import UsernameInput from "./UsernameInput";
import PasswordInput from "./PasswordInput";

class Login extends React.Component {

    constructor(props) {

        super(props);
        this.state = {
            email: "",
            username: "",
            password: "",
            passwordRepeat: ""
        };

        this.onEmailChange = this.onEmailChange.bind(this);
        this.onUsernameChange = this.onUsernameChange.bind(this);
        this.onPasswordChange = this.onPasswordChange.bind(this);
        this.onPasswordRepeatChange = this.onPasswordRepeatChange.bind(this);
        this.handleRegisterClick = this.handleRegisterClick.bind(this);
    }

    onEmailChange(email) {

        this.setState({
            email: email
        });
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

    onPasswordRepeatChange(passwordRepeat) {

        this.setState({
            passwordRepeat: passwordRepeat
        });
    }

    handleRegisterClick() {
        console.log("Execute Register with");
        console.log(this.state.email);
        console.log(this.state.username);
        console.log(this.state.password);
        console.log(this.state.passwordRepeat);
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
                <PasswordInput
                    password={this.state.passwordRepeat}
                    onPasswordChange={this.onPasswordRepeatChange}
                />
                <button onClick={this.handleRegisterClick}>Register</button>
                <p>
                    Already registered?
                    <a href="index.html">Login</a>
                </p>
            </div>
        );
    }
}

export default Login;
