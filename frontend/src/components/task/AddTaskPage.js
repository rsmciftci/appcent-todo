import React from 'react';
import TaskService from "../../service/TaskService";
import PageTitle from "../PageTitle";


class AddTaskPage extends React.Component{
    constructor(props) {
        super(props);

        this.state = this.initialState;
        this.handlerChange = this.handlerChange.bind(this)
    }
    initialState = {
        task: "",
        deadlineDate: "",
        deadlineTime: "",
        userId: ""
    }

    handlerChange(event) {
        this.setState({ [event.target.name]: event.target.value })
    }

    save = (e) => {
        e.preventDefault();


        const newTask = {
            task:this.state.task,
            deadlineDate:this.state.deadlineDate,
            deadlineTime:this.state.deadlineTime,
            userId:sessionStorage.getItem("userId")
        };
        console.log("=====>>>>>");
        console.log(newTask);
        TaskService.saveTask(newTask)
            .then(response => this.handleResponse(response))
            .catch(error => this.handleError(error))
        ;
    }


    handlerChange(event) {
        this.setState({ [event.target.name]: event.target.value })
    }

    handleResponse(response){
        if(response.status === 200){
            this.setState(this.initialState);
            alert("Task saved successfully");
        }
    }

    handleError(error){
        alert("Something Wrong!\nDuedate can't be past date.");
    }

    render() {


        return(

            <div className="container col-md-6 offset-md-3">

                <PageTitle title="Adding Task"></PageTitle>

                <form id="task-form" className="mt-5" onSubmit={this.save}>

                    <div className="form-row">

                        <div className="form-group">
                            <label htmlFor="inputTask">Task</label>
                            <textarea type="text"
                                   className="form-control"
                                    placeholder="Enter task"
                                      value={this.state.task}
                                      onChange={this.handlerChange}

                                   name="task" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputDeadlineDay">Deadline Day</label>
                            <input type="text"
                                   className="form-control"
                                   placeholder="YYYY-MM-DD"
                                   value={this.state.deadlineDate}
                                   onChange={this.handlerChange}
                                   name="deadlineDate" />
                        </div>

                        <div className="form-group ">
                            <label htmlFor="inputDeadlineTime">Deadline Time</label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="hh:mm:ss"
                                value={this.state.deadlineTime}
                                onChange={this.handlerChange}
                                name="deadlineTime" />
                        </div>


                    </div>

                    <input type="submit" className="btn btn-danger btn-block" value="Save" />



                </form>
            </div>



        )
    }


}

export default AddTaskPage;