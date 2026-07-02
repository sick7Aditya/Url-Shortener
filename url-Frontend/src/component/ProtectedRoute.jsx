import React from 'react'
import { Outlet , Navigate } from 'react-router-dom'


function ProtectedRoute() {
  
//   const user = localStorage.getItem("user");
  const email = localStorage.getItem("email");
  return email ? <Outlet/> : <Navigate to="/"/>

}

export default ProtectedRoute;
