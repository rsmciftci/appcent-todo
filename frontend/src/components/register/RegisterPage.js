import React, { Component } from 'react';
import {Card, Form, Button, Row,Col, Image} from 'react-bootstrap';
import axios from 'axios';



class RegisterPage extends Component {

    constructor (props){
        super(props);
        this.state = this.initialState;
        this.userChange = this.userChange.bind(this);
        this.submitUser = this.submitUser.bind(this);
    }
    initialState = {
        name:'',
        middleName:'',
        surname:'',
        email:'',
        password:''
    }

    resetCustomer = () =>{

        this.setState(() => this.initialState);
    }

    submitUser = event =>{
        event.preventDefault();

        const customer = {

            name : this.state.name,
            middleName :this.state.middleName,
            surname: this.state.surname,
            email :  this.state.email,
            password  : this.state.password

        };

        axios.post("http://localhost:8080/auth/register",customer).then(response =>{
            if(response.status === 201){
                this.setState(this.initialState);
                alert("Customer Saved Successfully");
            }
        }).catch(
            error => {
                alert("Something Went Wrong!\nMAKE SURE ALL VALUES IN THE CORRECT FORM!");
            }

        );

    }

    userChange(event){
        this.setState({
            [event.target.name]:event.target.value
        })

    }

    render() {
        const {name, middleName, surname, email, password
        } = this.state;


        return (


        <Card className={"row col-md-4 offset-md-4"} style={{border:"unset"}}>

                <Form onReset={this.resetCustomer} onSubmit={this.submitUser} id="customerFormId" >
                    <Image alt="" width="72" height="72" src="https://www.appcent.mobi/_next/image?url=%2Fassets%2Fsvg%2Fappcent-logo.svg&w=640&q=75"></Image>
                    <Card.Body>

                        <Row>
                            <Form.Group as={Col} controlId="formGridName">
                                <Form.Label className='text-black'>Name</Form.Label>
                                <Form.Control required autoComplete='off'
                                              name = "name"
                                              type="text"
                                              value={name}
                                              onChange={this.userChange}
                                              placeholder="Name" />
                                <Form.Label className='text-black'>Middle Name</Form.Label>
                                <Form.Control  autoComplete='off'
                                               name="middleName"
                                               type="text"
                                               value={middleName}
                                               onChange={this.userChange}
                                               placeholder="Enter Middle Name If Has One" />
                                <Form.Label className='text-black'>Surname</Form.Label>
                                <Form.Control required autoComplete='off'
                                              name="surname"
                                              type="text"
                                              value={surname}
                                              onChange={this.userChange}
                                              placeholder="Enter Surname" />
                                <Form.Label className='text-black'>Email</Form.Label>
                                <Form.Control required autoComplete='off'
                                              name="email"
                                              type="text"
                                              value={email}
                                              onChange={this.userChange}
                                              placeholder="Enter Email" />
                                <Form.Label className='text-black'>Password</Form.Label>
                                <Form.Control required autoComplete='off'
                                              name="password"
                                              type="text"
                                              value={password}
                                              onChange={this.userChange}
                                              placeholder="Enter Password" />

                            </Form.Group>



                        </Row>

                        <>
                            <style type="text/css">
                                {` .btn-flat { background-color: red;  color: white;  margin-top: 15px; margin-right: 5px;  } `}

                            </style>

                            <Button size ="sm" class="btn btn-primary btn-lg button-space" variant="flat" type="submit">
                                Submit
                            </Button>

                            <Button  size ="sm"  variant="flat" type="reset">
                                Reset
                            </Button>
                        </>

                    </Card.Body>


                </Form>

            </Card>

        );
    }
}

export default RegisterPage;