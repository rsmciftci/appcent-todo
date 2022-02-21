import React from "react";
import { Navbar, Container, Nav, NavDropdown } from 'react-bootstrap/';



class Menu extends React.Component {

    constructor(props){
        super(props)

        this.handleLogout = this.handleLogout.bind(this);
    }

    handleLogout(){
        this.props.logout();
    }

    render() {
        return <div className="col-md-0 offset-md-0 ">
            <Navbar bg="light" expand="lg">
                <Container>
                    <Navbar.Brand href="/">Appcent</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link href="/">Home</Nav.Link>



                            {this.props.isLoggedOn &&  <NavDropdown title="Tasks" id="basic-nav-dropdown">
                                <NavDropdown.Item href="/Add">Add Task</NavDropdown.Item>
                                <NavDropdown.Item href="/AllTasks">All Task</NavDropdown.Item>
                            </NavDropdown>}

                            {!this.props.isLoggedOn &&  <Nav.Link href="/login">Log In</Nav.Link>}
                            {this.props.isLoggedOn && <Nav.Link  href="/logout" onClick={this.handleLogout}>Log Out</Nav.Link>}
                            {!this.props.isLoggedOn &&  <Nav.Link href="/register">Register</Nav.Link>}

                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </div>


    }
}

export default Menu;