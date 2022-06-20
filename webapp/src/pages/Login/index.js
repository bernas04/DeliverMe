import React, { useState } from "react";
import ReactDOM from "react-dom";
import { useNavigate } from "react-router-dom";

import "./styles.css";


const LogIn = () => {
  
  // React States
    const [errorMessages, setErrorMessages] = useState({});
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [fetchError, setFetchError] = useState(false);
    const [processing, setProcessing] = useState(false);
    const [userInfo, setUserInfo] = useState({});

    const navigate = useNavigate();



  const submit = (event) => {
    //Prevent page reload
    event.preventDefault();
    
    var { uname, pass } = document.forms[0];



    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json',  'Access-Control-Allow-Origin': '*'},
        body: JSON.stringify({username: uname.value, password: pass.value})
    };
    
    fetch(`${process.env.REACT_APP_API_URL}/auth/authenticate`, requestOptions)
    .then(response => response.json())
    .then(data => { 
        if (data.error){
            setFetchError(true);
            setErrorMessages({name:"Erro", message:data.error})
        }
        if (data.token){
          console.log("Aqui")
            localStorage.setItem("info", JSON.stringify(data));
            setIsSubmitted(true);
        }
    });
    

  };

  // Generate JSX code for error message
  const renderErrorMessage = (name) =>
    name === errorMessages.name && (
      <div className="error">{errorMessages.message}</div>
    );

  // JSX code for login form
  const renderForm = (
    <div className="form">
      <form onSubmit={submit}>
        <div className="input-container">
          <label>Username </label>
          <input type="text" name="uname" required />
        </div>
        <div className="input-container">
          <label>Password </label>
          <input type="password" name="pass" required />
          {renderErrorMessage("Erro")}
        </div>
        <div className="button-container">
          <input type="submit" />
        </div>
      </form>
    </div>
  );

  return (
    <div className="app">
      <div className="login-form">
        <div className="title">Sign In</div>
        {isSubmitted ? navigate("/manage") : renderForm}
      </div>
    </div>
  );
}

export default LogIn;