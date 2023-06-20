import { useEffect, useState } from "react"
import { retrieveAllTodosForUsername } from "./api/TodoApiService"

export default function ListTodosComponent(){
    const today = new Date()
    const targetDate = new Date(today.getFullYear()+12, today.getMonth(), today.getDate())

    console.log(today.toDateString)
    const [todos, setTodos] = useState([])

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

    useEffect (
        () => refreshTodos(), [] //첫번째에만 로딩하겠다는 뜻
    )

    return (
        <div className="container">
            <h1>Things You Want to Do!</h1>
            <div>
                <table className='table'>
                    <thead>
                        <tr>
                            <td>id</td>
                            <td>description</td>
                            <td>is Done</td>
                            <td>Target Date</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            todos.map(
                                todo => (
                                    <tr key={todo.id}>
                                        <td>{todo.id}</td>
                                        <td>{todo.description}</td>
                                        <td>{todo.done.toString()}</td>
                                        <td>{todo.targetDate}</td>
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