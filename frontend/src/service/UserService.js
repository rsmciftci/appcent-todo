import axios from "axios";


class UserService{



    findUserID(username){

        const url = '/api/v1/user/' + username;
        const config = {
            headers: { Authorization: `${sessionStorage.getItem("token")}`}
        }

        return axios.get(url,config);
    }

}

export default new UserService();