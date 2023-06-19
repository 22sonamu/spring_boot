import { useState } from 'react';
import './TodoApp.css'
export default function TodoApp(){
    return (    
        <div className="TodoApp">
            Todo Management : Applicaiton
            <LoginComponent/>
            {/* <WelcomeComponent/> */}
        </div>
    );
}

function LoginComponent(){

    const [username, setUsername] = useState("in28minutes")

    const [password, setPassword] = useState("")

    function handleUsernameChange(event){
        setUsername(event.target.value)
    }

    function handlePasswordChange(event){
        setPassword(event.target.value)
    }

    return(
        <div className="Login">
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
                    <button type="button" name="login"> login </button>
                </div>
            </div>
        </div>
    )
}
function WelcomeComponent(){
    return(
        <div className="Welcome">
            Welcome Component
        </div>
    )
}