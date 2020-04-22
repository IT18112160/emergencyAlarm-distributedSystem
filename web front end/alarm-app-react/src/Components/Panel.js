import React, {Component} from 'react';
import {AppBar, Toolbar, Typography,LinearProgress,Button} from "@material-ui/core";
import './Panel.css'

class Panel extends Component {
    render() {
        return (
            <div>

                <AppBar position="static">
                    <Toolbar>

                        <Typography variant="h6" style={{marginLeft:'40%'}}>
                            Emergency Fire Alarm System
                        </Typography>

                        <Button variant={"contained"} color={"secondary"} style={{marginLeft:'35%'}} onClick={()=>{this.props.logout
                        ()}}>log Out</Button>

                    </Toolbar>
                </AppBar>


                <div className='container' >


                    <div className="sensor">

                        <h4>Sensor Name</h4>
                        <h6>CO2 Volume</h6>
                        <LinearProgress variant="buffer" value={50} valueBuffer={100} />

                        <h6>Smoke volume</h6>
                        <LinearProgress variant="buffer" value={70} valueBuffer={100} />

                    </div>





                </div>





            </div>
        );
    }
}

export default Panel;
