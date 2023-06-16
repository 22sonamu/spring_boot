
import FirstComponent, {FifthComponent} from './FirstComponent'
import SecondComponent from './SecondComponent'
import ThirdComponent from './ThirdComponent'
import FourthComponent from './FourthComponent'
//중괄호를 쓰지 않으면 , 무조건 기본 컴포넌트가 나온다 (default import)
//import FifthComponentfrom './FirstComponent'
//이렇게 작성하여도 FirstComponent가 사용된다.
//기본 컴포넌트가 아닌 일반 컴포넌트를 import 하려면 {} 중괄호 안에 넣어줘야한다. (named import)

export default function LearningComponent() {
    return (
      <div className="App">
        My Todo Application
        <FirstComponent />
        <SecondComponent></ SecondComponent>
        <ThirdComponent></ThirdComponent>
        <FourthComponent></FourthComponent>
        <FifthComponent/>
      </div>
    );
  }