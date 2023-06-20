
import './TodoApp.css'
import {BrowserRouter, Routes, Route, useNavigate, useParams, Link, Navigate} from 'react-router-dom'
import LogoutComponent from './LogoutComponent';
import FooterComponent from './FooterComponent';
import HeaderComponent from './HeaderComponent';
import ListTodosComponent from './ListTodosComponent';
import ErrorComponent from './ErrorComponent';
import WelcomeComponent from './WelcomeComponent';
import LoginComponent from './LoginComponent';
import AuthProvider, {useAuth} from './Security/AuthContext';
import TodoComponent from './TodoComponent'
export default function TodoApp(){
    
    function AuthenticatedRoute({children}){
        const authContext = useAuth()
        if(authContext.isAuthenticated){
            return children
        }
        return <Navigate to='/'></Navigate>
        
    }
    return (    
        <div className="TodoApp">
            <AuthProvider>
                <BrowserRouter>
                <HeaderComponent></HeaderComponent>
                    <Routes>
                        <Route path='/' element={<LoginComponent/>}/>
                        <Route path='/login' element={<LoginComponent/>}/>
                        <Route path='/welcome/:username' element={
                        <AuthenticatedRoute>
                        <WelcomeComponent/>
                        </AuthenticatedRoute>
                    }/>
                        {/* 위 루트중 아무곳에도 해당하지않으면  */}
                        <Route path='*' element={<ErrorComponent/>}></Route>
                        
                        <Route path='/todos' element={
                        <AuthenticatedRoute>
                        <ListTodosComponent/>
                        </AuthenticatedRoute>
                        }></Route>

                        <Route path='/todo/:id' element={
                            <AuthenticatedRoute>
                                <TodoComponent/>
                            </AuthenticatedRoute>
                        }></Route>
                        
                        <Route path='/logout' element={
                            <AuthenticatedRoute>
                        <LogoutComponent/>
                        </AuthenticatedRoute>
                        }/>
                    </Routes>   
                </BrowserRouter>
            </AuthProvider>
            
        </div>
    );
}












