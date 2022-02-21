import React from "react";
import AuthenticationService from "../../service/AuthenticationService";
import {Navigate} from "react-router-dom";
import UserService from "../../service/UserService";

class LoginPage extends React.Component{



    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: ""
        }

        this.handlerChange = this.handlerChange.bind(this)

    }

    handlerChange(event) {
        this.setState({ [event.target.name]: event.target.value })
    }

    handleFormSubmit = (e) => {
        e.preventDefault();
        AuthenticationService.login(this.state.username, this.state.password)
            .then(response => this.handleResponse(response))
            .catch(error => this.handleError(error));

    }

    handleResponseForId(response) {
        sessionStorage.setItem('userId', response.data)
        console.log(response)

    }

    handleErrorForId(error) {
        console.log(error.response)
    }

    getUserId(){
        UserService.findUserID(this.state.username)
            .then(response => this.handleResponseForId(response))
            .catch((error => this.handleErrorForId(error)));
    }

    handleResponse(response) {

        sessionStorage.setItem('token', response.data)
        sessionStorage.setItem('username', this.state.username)
        this.getUserId()


        this.props.login()
    }

    handleError(error) {
        console.log(error.response)
    }

    render() {
        if(sessionStorage.getItem('token')){
            return <Navigate to="/" ></Navigate>
        }


        return( <>

                <div className="container">
                    <div className="text-center mt-5">
                        <div className="row col-md-4 offset-md-4">

                            <form className="form-signin" onSubmit={this.handleFormSubmit}>

                                <img className="mb-4" src="https://www.appcent.mobi/_next/image?url=%2Fassets%2Fsvg%2Fappcent-logo.svg&w=640&q=75" alt="" width="72" height="72" />
                                <br/>

                                <label htmlFor="inputEmail" className="sr-only">Email</label>
                                <input
                                    type="email"
                                    id="inputEmail"
                                    className="form-control"
                                    placeholder="Email"
                                    required=""
                                    autoFocus=""
                                    value={this.state.username}
                                    name="username"
                                    onChange={this.handlerChange}
                                />

                                <label htmlFor="inputPassword" className="sr-only">Password</label>
                                <input
                                    type="password"
                                    id="inputPassword"
                                    className="form-control"
                                    placeholder="Password"
                                    required=""
                                    value={this.state.password}
                                    name="password"
                                    onChange={this.handlerChange}
                                />

                                <div className="checkbox mb-3">
                                    <label>
                                        <input type="checkbox" value="remember-me" /> Remember me
                                    </label>
                                </div>

                                <input type="submit" className="btn btn-danger btn-block" value="Log In" />
                                <p className="mt-5 mb-3 text-muted">© 2022</p>
                            </form>
                        </div>
                    </div>
                </div>

            </>

        )

    }

}

export default LoginPage;