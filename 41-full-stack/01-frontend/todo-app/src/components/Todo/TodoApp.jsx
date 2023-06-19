import { useState } from 'react';
import './TodoApp.css'
import {BrowserRouter, Routes, Route, useNavigate, useParams, Link} from 'react-router-dom'
export default function TodoApp(){
    return (    
        <div className="TodoApp">
            <HeaderComponent></HeaderComponent>
            <BrowserRouter>
                <Routes>
                    <Route path='/' element={<LoginComponent/>}/>
                    <Route path='/login' element={<LoginComponent/>}/>
                    <Route path='/welcome/:username' element={<WelcomeComponent/>}/>
                    {/* 위 루트중 아무곳에도 해당하지않으면  */}
                    <Route path='*' element={<ErrorComponent/>}></Route>
                    <Route path='/todos' element={<ListTodosComponent/>}></Route>
                    <Route path='/logout' element={<LogoutComponent/>}/>
                </Routes>
            </BrowserRouter>
            <FooterComponent></FooterComponent>
        </div>
    );
}

function LoginComponent(){

    const [username, setUsername] = useState("in28minutes")

    const [password, setPassword] = useState("")

    const [showSuccessMessage, setShowSuccessMessage] = useState(false);
    const [showErrorMessage, setShowErrorMessage] = useState(false);

    const navigate = useNavigate()

    function handleUsernameChange(event){
        setUsername(event.target.value)
    }

    function handlePasswordChange(event){
        setPassword(event.target.value)
    }

    function handleSubmit(){
        if(username==="in28minutes" && password==="dummy"){
            console.log("success")
            setShowSuccessMessage(true)
            setShowErrorMessage(false)
            navigate(`/welcome/${username}`)
        }else{
            console.log("fail")
            setShowErrorMessage(true)
            setShowSuccessMessage(false)
        }
    }

    return(
        <div className="Login">
            <h1>Time to Login</h1>
            {/* showSuccessMessage가 true이면 뒤의 div를 보여주겠다는 뜻 */}
            {showSuccessMessage && <div className='successMessage'>Authenticated Successfully</div>}
            {showErrorMessage && <div className='errorMessage'>Authenticated Failed</div>}
            <div className="LoginForm">
                <div>
                    <label>User Name</label>
                    <input type="text" name="username" value={username} onChange={handleUsernameChange}></input>
                </div>
                <div>
                    <label>Password</label>
                    <input type="password" name="password" value={password} onChange={handlePasswordChange}></input>
                </div>
                <div>
                    <button type="button" name="login" onClick={handleSubmit}> login </button>
                </div>
            </div>
        </div>
    )
}



function WelcomeComponent(){

    const {username} = useParams()

    console.log(username)

    // const params = useParams()
    // console.log(params.username) //위와 같은 뜻임

    return(
        <div>
            <h1>Welcome {username}</h1>
            <div className="Welcome">
                Manage Your todos. <Link to='/todos'>Go Here</Link>
            </div>
        </div>
    )
}

function ErrorComponent(){
    return (
        <div className="ErrorComponent">
            <h1>We are Working really hard!</h1>
            <div>Sorry</div>
        </div>
    )
}

function ListTodosComponent(){
    const today = new Date()
    const targetDate = new Date(today.getFullYear()+12, today.getMonth(), today.getDate())

    console.log(today.toDateString)

    const todos = [
        {id : 1, description :'Learn AWS', done : false, targetDate:targetDate },
        {id : 2, description :'Learn Full Stack Dev', done : false, targetDate:targetDate},
        {id : 3, description :'Learn DevOps', done :false, targetDate:targetDate}
    ]
    return (
        <div className="ListTodosComponent">
            <h1>Things You Want to Do!</h1>
            <div>
                <table>
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
                                        <td>{todo.targetDate.toDateString()}</td>
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

function HeaderComponent(){
    return (
        <div className="header">
            Header <hr />
        </div>
    )
}

function FooterComponent(){
    return (
        <div className="footer">
            <hr />
         </div>
    )
}

function LogoutComponent(){
    return (
        <div className="LogoutComponent">
            <h1>You are logged out</h1>
            <div>
                Thank you for using our App. Come back soon!
            </div>
        </div>
    )
}