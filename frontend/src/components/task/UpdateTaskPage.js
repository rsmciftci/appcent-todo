import React, { Component } from 'react';
import TaskService from "../../service/TaskService";
import PageTitle from "../PageTitle";
import { Button } from 'react-bootstrap';


class UpdateTaskPage extends Component {


    constructor (props){
        super(props);


        this.state = {

            id:'',
            task:'',
            isDone:'',
            deadlineDay:'',
            deadlineTime:'',
            creationDateTime:''
        };
        this.handlerChange = this.handlerChange.bind(this);
    }

    handlerChange(event) {
        this.setState({ [event.target.name]: event.target.value })
    }
    update = (e) => {
        e.preventDefault();
        this.state.isUpdated = true;


        const newTask = {
            id:this.props.taskId,
            task:this.state.task,
            deadlineDate:this.state.deadlineDay,
            done:this.state.isDone,
            deadlineTime:this.state.deadlineTime,
            creationDateTime:this.state.creationDateTime,
            userId:sessionStorage.getItem("userId")
        };
        console.log(newTask);
        TaskService.updateTask(newTask)
            .then(response => this.handleResponse(response))
            .catch(error => this.handleError(error))
        ;
    }

    handleError(error){
        alert("Something went wrong!");
    }

    handleResponse(response){
        if(response.status == 200){
            this.move();

            }
    }
    move() {
        window.location = "../AllTasks";
    }


    componentDidMount() {
        TaskService.findTaskById(this.props.taskId).then((res) =>{
            this.setState({id: res.data.id,
                task:res.data.task,
                isDone:res.data.done.toString(),
                deadlineDay: res.data.deadlineDate,
                deadlineTime: res.data.deadlineTime,
                creationDateTime: res.data.creationDateTime
            });
        })
    }

    render() {




        return (

            <div className="container col-md-6 offset-md-3">

                <PageTitle title="Updating Task"></PageTitle>

                <form id="task-form" className="mt-5" onSubmit={this.update}>

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
                            <label htmlFor="inputDeadlineDay">Deadline Time</label>
                            <input type="text"
                                   className="form-control"
                                   placeholder="hh:mm:ss"
                                   value={this.state.deadlineDay}
                                   onChange={this.handlerChange}
                                   name="deadlineDay" />
                        </div>

                        <div className="form-group">
                            <label htmlFor="inputDeadlineTime">Deadline Time</label>
                            <input type="text"
                                   className="form-control"
                                   placeholder="hh:mm:ss"
                                   value={this.state.deadlineTime}
                                   onChange={this.handlerChange}
                                   name="deadlineTime" />
                        </div>


                        <div className="form-group ">
                            <label htmlFor="inputIsDone">Is Done</label>
                            <input
                                type="text"
                                className="form-control"
                                value={this.state.isDone}
                                onChange={this.handlerChange}
                                name="isDone" />
                        </div>


                    </div>

                    <style type="text/css">
                        {` .btn-flat { background-color: blue;  color: white;  margin-top: 15px; margin-right: 5px;  } `}

                    </style>

                    <Button onClick={ () => this.update}   size ="sm" class="btn btn-primary btn-lg button-space" variant="flat" type="submit">
                        Update
                    </Button>





                </form>
            </div>
        );
    }
}

export default UpdateTaskPage;