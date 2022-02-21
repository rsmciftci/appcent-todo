import React, { Component, useState, useEffect } from 'react';
import TaskService from "../../service/TaskService";
import {Button} from "react-bootstrap";

class AllTaskPage extends Component {


    constructor (props){
        super(props);

        this.state = {
            tasks: []
        };
        this.deleteTask = this.deleteTask.bind(this);
        this.editTask = this.editTask.bind(this);
    }


    componentDidMount() {
        TaskService.findAllTasks().then((res) =>{
            this.setState({tasks: res.data});
        })
    }

    editTask(id) {
        console.log(id);
        this.props.history.push("/UpdateTask/"+id);
    }


    deleteTask(taskId){
        TaskService.deleteTask(taskId).then(
            res => { this.setState({tasks: this.state.tasks.filter(task => task.id !== taskId)}) }
        );

    }

    render() {


        return (

           <div>
               <h2 className="text-center">Task List</h2>
               <div className="row">
                   <table className= "table table-striped table-bordered">
                       <thead>
                        <tr>
                            <th>Done</th>
                            <th>Deadline Day</th>
                            <th>Deadline Time</th>
                            <th>Task</th>
                            <th>Action</th>
                        </tr>
                       </thead>
                       <tbody>
                       {
                           this.state.tasks.map(
                               task =>
                                   <tr key = {task.id}>

                                       <td>{task.done.toString()}</td>
                                       <td>{task.deadlineDate}</td>
                                       <td>{task.deadlineTime}</td>
                                       <td>{task.task}</td>
                                       <td>
                                           <style type="text/css">
                                               {` .btn-flat { background-color: blue;  color: white;  margin-top: 5px; margin-right: 5px;  } `}
                                               {` .btn-delete { background-color: red;  color: white;  margin-top: 5px; margin-right: 5px;  } `}
                                           </style>

                                           <Button href={"UpdateTask/" + task.id} size ="sm" class="btn btn-primary btn-lg button-space" variant="flat" type="submit">
                                               Update
                                           </Button>
                                           <Button onClick={ () => this.deleteTask(task.id)} size ="sm" class="btn btn-primary btn-lg button-space" variant="delete" type="submit">
                                               Delete
                                           </Button>

                                       </td>

                                   </tr>
                           )
                       }

                       </tbody>

                   </table>

               </div>

           </div>
        );
    }
}

export default AllTaskPage;