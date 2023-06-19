import { useState } from 'react';
import './TodoApp.css'
import {BrowserRouter, Routes, Route, useNavigate} from 'react-router-dom'
export default function TodoApp(){
    return (    
        <div className="TodoApp">
            <BrowserRouter>
                <Routes>
                    <Route path='/' element={<LoginComponent/>}></Route>
                    <Route path='/login' element={<LoginComponent/>}></Route>
                    <Route path='/welcome' element={<WelcomeComponent/>}></Route>
                    {/* 위 루트중 아무곳에도 해당하지않으면  */}
                    <Route path='*' element={<ErrorComponent/>}></Route>
                </Routes>
            </BrowserRouter>
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
            navigate('/welcome')
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
    return(
        <div>
            <h1>Welcome in28minutes</h1>
            <div className="Welcome">
                Welcome Component
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