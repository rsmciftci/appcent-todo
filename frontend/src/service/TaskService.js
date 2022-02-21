import axios from "axios";

class TaskService{

    saveTask(task){
        const url = "/api/v1/task";
        return axios.post(url,task);
    }

    updateTask(task){
        const url = "/api/v1/task";
        return axios.put(url,task);
    }

    findAllTasks(){
        const userId = sessionStorage.getItem("userId");
        const url = "/api/v1/task/find-all-by-userId/" + userId;
        return axios.get(url);
    }


    deleteTask(taskId){
        const url = "/api/v1/task/" + taskId;
        return axios.delete(url);
    }

    findTaskById(taskId){
        const url = "/api/v1/task/" + taskId;
        const config = {
            headers: { Authorization: `${sessionStorage.getItem("token")}`}
        }
        return axios.get(url,config);
    }
}

export default new TaskService();