
import './App.css';
import TodoApp from './components/Todo/TodoApp'



function App() {
  return (
    <div className="App">
      <TodoApp></TodoApp>
    </div>
  );
}

// function PlayingWithProps(properties){
//   console.log(properties.property1)
//   console.log(properties.property2)
//   return (
//     <div>props</div>
//   )
// }


function PlayingWithProps({property1, property2}){
  console.log(property1)
  console.log(property2)
  return (
    <div>props</div>
  )
}

export default App;
