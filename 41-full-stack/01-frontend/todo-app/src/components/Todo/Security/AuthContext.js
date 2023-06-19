//1. Create a Context

import { createContext, useState, useContext} from "react";

export const AuthContext = createContext()


export const useAuth = () => useContext(AuthContext)


//2.Share the created context with other components


export default function AuthProvider({children}){

    //Put some state in context
    const [number, setNumber] = useState(10)

    const [isAuthenticated, setAuthenticated] = useState(false);

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
        <AuthContext.Provider value={{number , isAuthenticated, setAuthenticated}}>
            {children}
        </AuthContext.Provider>
    )
}