
import './TodoApp.css'
import {BrowserRouter, Routes, Route, useNavigate, useParams, Link} from 'react-router-dom'
import LogoutComponent from './LogoutComponent';
import FooterComponent from './FooterComponent';
import HeaderComponent from './HeaderComponent';
import ListTodosComponent from './ListTodosComponent';
import ErrorComponent from './ErrorComponent';
import WelcomeComponent from './WelcomeComponent';
import LoginComponent from './LoginComponent';
import AuthProvider from './Security/AuthContext';
export default function TodoApp(){
    return (    
        <div className="TodoApp">
            <AuthProvider>
                <BrowserRouter>
                <HeaderComponent></HeaderComponent>
                    <Routes>
                        <Route path='/' element={<LoginComponent/>}/>
                        <Route path='/login' element={<LoginComponent/>}/>
                        <Route path='/welcome/:username' element={<WelcomeComponent/>}/>
                        {/* 위 루트중 아무곳에도 해당하지않으면  */}
                        <Route path='*' element={<ErrorComponent/>}></Route>
                        <Route path='/todos' element={<ListTodosComponent/>}></Route>
                        <Route path='/logout' element={<LogoutComponent/>}/>
                    </Routes>   
                </BrowserRouter>
            </AuthProvider>
            
        </div>
    );
}












