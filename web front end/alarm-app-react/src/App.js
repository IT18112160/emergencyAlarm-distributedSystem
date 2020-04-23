import React from 'react';
import logo from './logo.svg';
import './App.css';
import {useDispatch, useSelector} from "react-redux";
import signin from "./Actions/signin";
import Login from "./Components/Login";
import Panel from "./Components/Panel";
import signout from "./Actions/signout";

function App() {

  const log= useSelector(state=>state.logged)
  const dispatch= useDispatch();

  const login=()=>{
    dispatch(signin())
  }




if(log===false){

  return <div>
    <button onClick={()=>dispatch(signin())}>Sign in</button>
    <Login login={login} />
  </div>
}
if(log===true){
  return <div>
    <Panel logout={login}/>
  </div>
}
}

export default App;
