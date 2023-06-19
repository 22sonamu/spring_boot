import {PropTypes} from 'prop-types'
export default function CounterButton({by, increamentCounterParentFunction, decreaseCounterParentFunction}){ //하위 컴포넌트에서는 상위 컴포넌트의 메서드에 접근할 수없다.


    console.log(by);

    // function incrementCountFunction(){
    //     increamentCounterParentFunction(by);
    // }

    // function decreaseCountFunction(){
    //     decreaseCounterParentFunction(by);
    // }  

    
    return (
        <div className="Counter">
            <div></div>
            <button className="counterButton" 
                    onClick={() => increamentCounterParentFunction(by)}  
                    >{by}</button>
            <button className="counterButton" 
                    onClick={() => decreaseCounterParentFunction(by)}  
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