import {useParams} from 'react-router-dom';
import UpdateTaskPage from "./UpdateTaskPage";


const UpdateTaskFunc = () => {

    const params = useParams();

    return <UpdateTaskPage taskId={params.id}></UpdateTaskPage>
}

export default UpdateTaskFunc;