export default function FirstComponent(){ //FirstComponent를 내보낸다.(export)
    return (
      <div className="FirstComponent">First Component</div>
    )
} //모듈 당 하나의 export default만 가능함

export function FifthComponent(){ //FirstComponent를 내보낸다.(export)
    return (
      <div className="FifthComponent">Fifth Component</div>
    )
}