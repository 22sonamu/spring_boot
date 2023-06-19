//1. Create a Context

import { createContext, useState, useContext} from "react";

export const AuthContext = createContext()


export const useAuth = () => useContext(AuthContext)

//2.Share the created context with other components


export default function AuthProvider({children}){

//Put some state in context
const [number, setNumber] = useState(10)

    setInterval(
        () => setNumber(number+1) , 10000 //10초마다 number + 1
    )


    return (
        <AuthContext.Provider value={{number}}>
            {children}
        </AuthContext.Provider>
    )
}