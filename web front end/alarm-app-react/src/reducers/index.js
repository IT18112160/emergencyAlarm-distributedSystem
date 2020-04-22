import {combineReducers} from "redux";
import loogedReducer from'./loggedreducer'

const allreducers= combineReducers({
    logged: loogedReducer
})


export default  allreducers;
