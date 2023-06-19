//1. Create a Context

import { createContext, useState } from "react";

export const AuthContext = createContext()


//Share the created context with other components


export default function AuthProvider({children}){

//2. Put some state in context

    const [number, setNumber] = useState(0)

    return (
        <AuthContext.Provider value={{number}}>
            {children}
        </AuthContext.Provider>
    )
}