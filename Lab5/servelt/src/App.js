import logo from './logo.svg';
import './App.css';
import {useEffect,useState} from "react";
import "../node_modules/bootstrap/dist/css/bootstrap.css";

function App() {
  const [value,setValue]=useState(1);
  const [lis,setLis]=useState([]);
function sendFunction(e)
{
  console.log(e.target.value);
}
useEffect(()=>{
  fetch("http://localhost:8080/Matrix").then(response=>{
    if(response.ok)
    {
      console.log("done");
      return response.json();
    }
    else{
      console.log("failed");
    }
  }).then(data=>
    {
      console.log(data);
    })
})

function putInputs(e){
  setValue(e.target.value);
  console.log("value"+value);
  const values=[];
  var cols=Math.ceil(12/value);
    for (var i=0; i<value*value; i++) {
      console.log("for loop");
      values.push(<input type="number" placeholder='0'  className={"col-lg-"+cols} name={'input'+i}></input>);
    }
    setLis(values);
}
  return (
    <div className="App">
      <form action='http://localhost:8080/Matrix' >
        <div className="row" style={{display:"flex",flexWrap: "wrap",alignItems:"center"}}>
        {lis}
        </div>
        <input type="number"  value={value} onChangeCapture={putInputs} name='size' className='mt-5 '></input>
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
