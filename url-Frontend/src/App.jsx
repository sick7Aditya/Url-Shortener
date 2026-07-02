import { useState } from 'react'
import './App.css'
import OneAndOnlyPage from './component/OneAndOnlyPage'
import { BrowserRouter,Routes,Route } from 'react-router-dom'
import SignUp from './authCity/SignUp'
import Login from './authCity/Login'
import About from './More/About'
import Profile from './More/Profile'
import ProtectedRoute from './component/ProtectedRoute'
import { Link } from 'react-router-dom'

function Error(){
  return (
    <div>Go Back To your country Monkey!!!!<Link to='/'>Login</Link></div>
  )
}

function App() {
  return (
    <>
      <BrowserRouter>
          <Routes>
            <Route path="/signup" element={<SignUp/>}></Route>
            <Route path="/" element={<Login/>}></Route>
            <Route element={<ProtectedRoute/>}>
                <Route path="/Home" element={<OneAndOnlyPage/>}></Route>
                <Route path="/Profile" element={<Profile/>}></Route>
                <Route path="/About" element={<About/>}></Route>
            </Route>
           <Route path="/*" element={<Error/>}></Route>
          </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
