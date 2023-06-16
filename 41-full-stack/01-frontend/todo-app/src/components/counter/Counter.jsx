import {useState} from 'react'
import './Counter.css'

export default function Counter({by}){

    //두개의 요소를 가짐 [0 , f]
    const [count, setCount] = useState(0);

    console.log(by);

    function incrementCountFunction(){
        setCount(count+by);
        console.log(count);
    }

    function decreaseCountFunction(){
        setCount(count-by);
        console.log(count);
    } 

    
    return (
        <div className="Counter">
            <span className="count">{count}</span>
            <div></div>
            <button className="counterButton" 
                    onClick={incrementCountFunction}  
                    >{by}</button>
            <button className="counterButton" 
                    onClick={decreaseCountFunction}  
                    >-{by}</button>
        </div>
    )
}