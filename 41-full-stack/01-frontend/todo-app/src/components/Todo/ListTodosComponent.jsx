import { useEffect, useState } from "react"
import { deleteTodoApi, retrieveAllTodosForUsername } from "./api/TodoApiService"

export default function ListTodosComponent(){
    const today = new Date()
    const targetDate = new Date(today.getFullYear()+12, today.getMonth(), today.getDate())

    console.log(today.toDateString)
    const [todos, setTodos] = useState([])
    const [message, setMessage]  = useState(null)

    function refreshTodos(){
        retrieveAllTodosForUsername('in28minutes')
        .then(
            (response) => {
                console.log(response)
                setTodos(response.data)
            }
        ).catch(    
            error => console.log(error)
        )
    }

    function deleteTodo(id){
        deleteTodoApi('in28minutes', id)
        .then(
            (response) => {

                //1. display message
                setMessage(`Delete of todo with ${id}`)
                //2. update Todos
                refreshTodos()
            }
        ).catch(    
            error => console.log(error)
        )
    }

    useEffect (
        () => refreshTodos(), [] //첫번째에만 로딩하겠다는 뜻
    )

    return (
        <div className="container">
            <h1>Things You Want to Do!</h1>
            {message && <div className="alert alert-warning">{message}</div>}
            <div>
                <table className='table'>
                    <thead>
                        <tr>
                            <th>description</th>
                            <th>is Done</th>
                            <th>Target Date</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            todos.map(
                                todo => (
                                    <tr key={todo.id}>
                                        <td>{todo.description}</td>
                                        <td>{todo.done.toString()}</td>
                                        <td>{todo.targetDate}</td>
                                        <td><button className="btn btn-warning" onClick={() => deleteTodo(todo.id)}>Delete</button></td>
                                    </tr>
                                )
                            )
                        }
                        
                    </tbody>
                </table>
            </div>
        </div>
    )
}