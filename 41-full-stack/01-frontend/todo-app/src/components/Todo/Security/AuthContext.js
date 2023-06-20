//1. Create a Context

import { createContext, useState, useContext} from "react";
import {executeBasicAuthenticationService} from '../api/HelloWorldApiService'
import { apiClient } from "../api/ApiClient";

export const AuthContext = createContext()


export const useAuth = () => useContext(AuthContext)


//2.Share the created context with other components


export default function AuthProvider({children}){

    //Put some state in context

    const [isAuthenticated, setAuthenticated] = useState(false);

    const [username, setUsername] = useState(null)

    const [token , setToken] = useState(null)

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

    async function login(username, password){
        const baToken = 'Basic ' + window.btoa(username + ":" + password); //base64 인코딩
        try{
            const response = await executeBasicAuthenticationService(baToken)

            if(response.status == 200){
                setAuthenticated(true)
                setUsername(username)
                setToken(baToken)
                //api Client를 거치는 모든 요청에 interceptor를 생성하고 특수한 로직을 적용시킨다.
                apiClient.interceptors.request.use(
                    (config) => {
                        console.log('intercepting and adding a token')
                        config.headers.Authorization = baToken
                        return config
                    }
                )
                return true
            }else{
                logout()
                return true
            }
        }catch(error){
            logout()
            return false
        }

        console.log("test") //이 구문은 위의 코드가 실행되기 전에 실행될수있다. -> async method로 만들어야함 (await + async) -> 상위 method 도 async 가 되어야함 (Login Component - handleSubmit)
        setAuthenticated(false)

    }

    function logout(){
        setAuthenticated(false)
        setToken(null)
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
        <AuthContext.Provider value={{isAuthenticated, login, logout, username, token}}>
            {children}
        </AuthContext.Provider>
    )
}