import React, {Component} from 'react';
import {AppBar,Typography,Toolbar,Avatar,TextField,Button} from "@material-ui/core";
import './Login.css'
import {useSelector} from "react-redux";

class Login extends Component {

    constructor(props) {
        super(props);

        this.state={
            username:'',
            password:''
        }



    }


    handlechange=(e)=>{

        e.preventDefault();
        this.setState({
            [e.target.name]: e.target.value
        })

        console.log(this.state)

    }




    login=(e)=>{


        const user= {
            username: this.state.username,
            password: this.state.password,
            type:'user'
        }


        fetch('http://localhost:8080/login',{
            method:'POST',
            headers:{
                'content-type': 'application/json',
                'Accept': 'application/json'
            },
            body:JSON.stringify(user)


        }).then(res=>{
            console.log(res)

        }).then(data=>{
        })





    }






    render() {
        return (
            <div>
                <AppBar position="static">
                    <Toolbar>

                        <Typography variant="h6" style={{marginLeft:'40%'}}>
                           Emergency Fire Alarm System
                        </Typography>

                    </Toolbar>
                </AppBar>


                <div className='login-div'>
                    <h1 style={{marginLeft:'90px'}}>Login</h1>

                    <Avatar style={{width:'120px',height:'100px',marginLeft:'80px',marginBottom:'20px'}}></Avatar>

                    <div className='ínput-div'>
                    <TextField name='username' id="outlined-basic" label="Username" variant="outlined" className="textfield" onChange={this.handlechange} />
                    <br />
                    <TextField name='password' id="outlined-basic" label="Password" variant="outlined" className="textfield" type='password' onChange={this.handlechange}/>
                    </div>

                    <Button style={{marginTop:'20px',width:'300px'}} variant="contained" color="primary" onClick={()=>{this.props.login()}}>
                        Login
                    </Button>
                </div>
                
            </div>
        );
    }
}

export default Login;
