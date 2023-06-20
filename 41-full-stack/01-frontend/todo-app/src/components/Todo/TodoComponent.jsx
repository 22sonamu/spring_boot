import { useParams } from "react-router-dom"
import { retrieveTodoApi } from "./api/TodoApiService"
import { useAuth } from "./Security/AuthContext"
import { useEffect, useState } from "react"

export default function TodoComponent(){

    const {id} = useParams()
    const authContext=useAuth()
    const username = authContext.username
    const [description , setDescription] = useState('')

    useEffect(
        ()=>retrieveTodos, [id] //id가 바뀔때마다 실행
    )

    function retrieveTodos(){
        retrieveTodoApi(username, id)
        .then(
            (response) => {
                setDescription(response.data.description)
                
            }
        )
        .catch(error=>console.log(error))
    }

    return (
        <div className="container">
            <h1>Enter Todo Details</h1>
            <div>
                description : {description}
            </div>
        </div>
    )
}