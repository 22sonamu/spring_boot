import {useState} from 'react'
import './Counter.css'

export default function Counter(){

    //두개의 요소를 가짐 [0 , f]
    const [count, setCount] = useState(0);

    function incrementCountFunction(){
        setCount(count+1);
        console.log(count);
    }


    return (
        <div className="Counter">
            <span className="count">{count}</span>
            <div></div>
            <button className="counterButton" 
                    onClick={incrementCountFunction}
                               
                    >+1</button>
        </div>
    )
}