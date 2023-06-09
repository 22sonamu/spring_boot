import {Link} from 'react-router-dom';
import { useAuth } from './Security/AuthContext';
export default function HeaderComponent(){


    const authContext = useAuth();
    const isAuthenticated = authContext.isAuthenticated

    function logout(){
        authContext.logout()
    }


    return (

        <header className="border-bottom border-light border-5 mb-5 p-2 ">
            <div className='container'>
                <div className='row'>
                    <nav className='navbar navbar-expand-lg'>
                        <a className='navbar-brand ms-2 fs-2 fw-bold text-black' href='https://www.in28minutes.com'>in28minutes</a>
                        {/* Link는 Router 요소에만 사용가능하다.(HeaderComponent) */}
                        <div className='collapse navbar-collapse'>
                            <ul className='navbar-nav'>
                                {isAuthenticated && <li className='nav-item'><Link className='nav-link' to='/welcome/in28minutes'>Home</Link></li>}
                                {isAuthenticated &&  <li className='nav-item'><Link className='nav-link' to='/todos'>Todos</Link></li>}
                            </ul>
                        </div>
                        <ul className='navbar-nav'>
                                 {!isAuthenticated && <li className='nav-item'><Link className='nav-link' to='/login'>Login</Link></li>}
                                {isAuthenticated && <li className='nav-item'><Link className='nav-link' to='/logout' onClick={logout}>Logout</Link></li>}
                        </ul>

                    </nav>
                </div>
            </div>
        </header>
    )
}