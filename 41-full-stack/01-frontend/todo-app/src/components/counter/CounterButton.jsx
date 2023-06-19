import {PropTypes} from 'prop-types'
import {useState} from 'react'
export default function CounterButton({by, increamentCounterParentFunction, decreaseCounterParentFunction}){ //하위 컴포넌트에서는 상위 컴포넌트의 메서드에 접근할 수없다.

    //두개의 요소를 가짐 [0 , f]
    const [count, setCount] = useState(0);

    console.log(by);

    function incrementCountFunction(){
        setCount(count+by);
        increamentCounterParentFunction(by);
    }

    function decreaseCountFunction(){
        setCount(count-by);
        decreaseCounterParentFunction(by);
    }  

    
    return (
        <div className="Counter">
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

CounterButton.proTypes = {
    by : PropTypes.number
}

CounterButton.defaultProps= {
    by:1
}