import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import Menu from "./components/menu/Menu";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import LoginPage from "./components/login/LoginPage";
import RegisteringPage from "./components/register/RegisterPage";
import AddTaskPage from "./components/task/AddTaskPage";
import AllTaskPage from "./components/task/AllTaskPage";
import UpdateTaskPage from "./components/task/UpdateTaskPage";
import UpdateTaskFunc from "./components/task/UpdateTaskFunc";



class App extends React.Component{
    constructor(props) {
        super(props);

        this.state = {
            isLoggedOn: sessionStorage.getItem('token')

        }
        this.login = this.login.bind(this)
        this.logout = this.logout.bind(this)
    }

    login(){
        this.setState({isLoggedOn:true})
    }

    logout(){
        this.setState({isLoggedOn:false})
        sessionStorage.clear();
    }




       render() {
           return (
               <div className="App">
                   <Router>
                       <Menu isLoggedOn={this.state.isLoggedOn} logout={this.logout}></Menu>

                       <Routes>
                           <Route path="/login" element={<LoginPage login={this.login}/>}></Route>
                           <Route path="/register" element={<RegisteringPage login={this.login}/>}></Route>
                           <Route path="/Add" element={<AddTaskPage />}></Route>
                           <Route path="/AllTasks" element={<AllTaskPage />}></Route>
                           <Route path="/UpdateTask/:id" element={<UpdateTaskFunc></UpdateTaskFunc>}></Route>

                       </Routes>
                   </Router>

               </div>
           );
       }


}

export default App;