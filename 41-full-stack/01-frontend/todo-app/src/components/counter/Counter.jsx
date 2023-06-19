import {useState} from 'react'
import './Counter.css'
import CounterButton from './CounterButton';
export default function Counter(){

    const [count, setCount] = useState(0);

    function increamentCounterParentFunction(by){
        setCount(count+by);
    }
    function decreaseCounterParentFunction(by){
        setCount(count-by);
    }


    return (
        <div>
            <span className="totalCount">{count}</span>
            <CounterButton by={1} increamentCounterParentFunction={increamentCounterParentFunction} decreaseCounterParentFunction={decreaseCounterParentFunction}/>
            <CounterButton by={2} increamentCounterParentFunction={increamentCounterParentFunction} decreaseCounterParentFunction={decreaseCounterParentFunction}/>
            <CounterButton by={5} increamentCounterParentFunction={increamentCounterParentFunction} decreaseCounterParentFunction={decreaseCounterParentFunction}/>
        
        </div> 
    )
}

