import { useContext } from "react"
import { AuthContext } from "./Security/AuthContext"

export default function FooterComponent(){
    const authContext = useContext(AuthContext)
    console.log(`Fotter component-${authContext.number}`)
    return (
        <footer className="footer">

            <div className='container'>
                Your Footer
            </div>
            <hr />
         </footer>
    )
}