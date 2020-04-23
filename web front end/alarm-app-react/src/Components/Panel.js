import React, {Component} from 'react';
import {AppBar, Toolbar, Typography,LinearProgress,Button} from "@material-ui/core";
import './Panel.css'

class Panel extends Component {

    constructor(props) {
        super(props);

        this.state={
            sensors: []
        }
    }


componentWillMount() {


setInterval(()=> {

    fetch('http://localhost:8080/getallsensors', {mode: "cors"}).then(res => {
        return res.json()
    }).then(data => {
        console.log(data)
        this.setState({sensors: data})
    })

},1000);

}


    render() {
        return (
            <div>

                <AppBar position="static">
                    <Toolbar>

                        <Typography variant="h6" style={{marginLeft:'40%'}}>
                            Emergency Fire Alarm System
                        </Typography>

                        <Button variant={"contained"} color={"secondary"} style={{marginLeft:'35%'}} onClick={()=>this.props.logout()}>log Out</Button>

                    </Toolbar>
                </AppBar>


                <div className='container' >

                    {
                        this.state.sensors.map(sensor=>{

                        return (<div className="sensor" key={sensor.id}>

                                <h4>SensorName:{sensor.sensorName}  location:{"Floor:"+sensor.floorNo +"  "+ "Room:"+sensor.roomNo}</h4>
                                <h6>CO2 Volume:{sensor.co2}</h6>
                                <LinearProgress variant="buffer" value={sensor.co2*10} valueBuffer={100} color={sensor.co2>5? 'secondary': 'primary'}/>

                                <h6>Smoke volume:{sensor.smoke}</h6>
                                <LinearProgress variant="buffer" value={sensor.smoke*10} valueBuffer={100} color={sensor.smoke>5? 'secondary': 'primary'}/>

                            </div>)



                    })

                    }



                </div>





            </div>
        );
    }
}

export default Panel;
