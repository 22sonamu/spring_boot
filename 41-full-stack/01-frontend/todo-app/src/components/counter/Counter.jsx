import './Counter.css'

export default function Counter(){


    function incrementCountFunction(){
        console.log('increment clicked')
    }


    return (
        <div className="Counter">
            <span className="count">0</span>
            <div></div>
            <button className="counterButton" 
                    onClick={incrementCountFunction}
                               
                    >+1</button>
        </div>
    )
}