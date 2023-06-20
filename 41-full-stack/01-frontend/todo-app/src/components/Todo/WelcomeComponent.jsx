import {Link, useParams} from 'react-router-dom';
import { useState } from 'react';
import { retrieveHelloWorldBean, retrieveHelloWorldPathVariable } from './api/HelloWorldApiService';
import { useAuth } from './Security/AuthContext';
export default function WelcomeComponent(){

    const {username} = useParams()
    const [message, setMessage] = useState(null)
    const authContext = useAuth()
    // const params = useParams()
    // console.log(params.username) //위와 같은 뜻임

    function callHelloWorldRestApi(){

        retrieveHelloWorldPathVariable('mara', authContext.token)
        .then(
            //만약 response가 돌아오면 successfulResponse를 실행한다.
            (response) => successfulResponse(response)
        )
        .catch(
            (error) => errorResponse(error)
        ) 
        .finally( // 요청 성공 여부와 무관
            () => console.log('cleanup')
        )
    }
    function successfulResponse(response){
        console.log(response)
        //setMessage(response.data)
        setMessage(response.data.message)
    }
    function errorResponse(error){
        console.log(error)
    }

    return(
        <div className="Welcome">
            <h1>Welcome {username}</h1>
            <div>
                Manage Your todos. <Link to='/todos'>Go Here</Link>
            </div>
            <div>
                <button className='btn btn-success m-S' onClick={callHelloWorldRestApi}>Call Hello World REST API</button>
            </div>
            <div className='text-info'>
                {message}
            </div>
        
        </div>
        
    )
}