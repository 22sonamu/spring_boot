//1. Create a Context

import { createContext, useState, useContext} from "react";
import {executeBasicAuthenticationService} from '../api/HelloWorldApiService'

export const AuthContext = createContext()


export const useAuth = () => useContext(AuthContext)


//2.Share the created context with other components


export default function AuthProvider({children}){

    //Put some state in context

    const [isAuthenticated, setAuthenticated] = useState(false);

    const [username, setUsername] = useState(null)

    // function login(username, password){

    //     if(username==="in28minutes" && password==="dummy"){
    //         setUsername(username)
    //         setAuthenticated(true)
    //         return true;
    //     }else{
    //         setAuthenticated(false)
    //         setUsername(null)
    //         return false;        
    //     }

    // }

    function login(username, password){
        const baToken = 'Basic ' + window.btoa(username + ":" + password); //base64 인코딩
        executeBasicAuthenticationService(baToken)
        .then(response => console.log(response))
        .catch(error => console.log(error))

        setAuthenticated(false)

    }

    function logout(){
        setAuthenticated(false)
    }
    //const valueToShared = {number , isAuthenticated, setAuthenticated}

    //이 객체는 
    // {
    //     number : number,
    //     isAuthenticated : isAuthenticated ,
    //     setAuthenticated : setAuthenticated 
    // }  이렇게 저장된다. 그래서 userAuth.name 이런식으로 접근 가능한것이다.

    // setInterval(
    //     () => setNumber(number+1) , 10000 //10초마다 number + 1
    // )


    return (
        <AuthContext.Provider value={{isAuthenticated, login, logout, username}}>
            {children}
        </AuthContext.Provider>
    )
}