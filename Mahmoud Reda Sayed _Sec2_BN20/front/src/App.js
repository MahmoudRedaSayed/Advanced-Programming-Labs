import logo from './logo.svg';
import './App.css';
import {useEffect,useState} from "react";
import "../node_modules/bootstrap/dist/css/bootstrap.css";

function App() {
  const [value,setValue]=useState(0);
  const [lis,setLis]=useState([]);
function sendFunction(e)
{
  if(e.target.value>0)
  setValue(e.target.value);
}

function putInputs(e){
  if(e.target.value>=0)
  {
    setValue(e.target.value);
    console.log(e.target.value);
    const values=[];
    var cols=Math.floor(100/e.target.value);
    for (var i=0; i<e.target.value*e.target.value; i++) {
      console.log("for loop");
      values.push(<input type="number" placeholder='0'  style={{width:cols+"%"}} name={'input'+i}></input>);
    }
    setLis(values);

  }
  }
  return (
    <div className="App">
      <form action='http://localhost:8080/Matrix' method="Post" >
        <div style={{display:"flex",flexWrap: "wrap",alignItems:"center",justifyContent:"center"}}>
        {lis}
        </div>
        <input type="number"  value={value} onChange={putInputs} name='size' className='mt-5 '></input>
        <div className='mt-5'>
        <input className="form-check-input" type="radio" name='trans' value="" id="defaultCheck1"></input>
        <label className="form-check-label" for="defaultCheck1">
          Transpose
        </label>

          <input className="form-check-input" type="radio" name='deter' value="" id="defaultCheck2" ></input>
          <label className="form-check-label" for="defaultCheck2">
            determaint
          </label>
          </div>
        <button type="submit" className='btn btn-primary mt-5'> send values</button>
        </form>
    </div>
  );
}

export default App;
